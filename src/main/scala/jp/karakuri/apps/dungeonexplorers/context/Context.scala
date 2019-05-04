package jp.karakuri.apps.dungeonexplorers.context

import jp.karakuri.apps.dungeonexplorers.layer.{GuiLayer, ObjectLayer}
import jp.karakuri.apps.dungeonexplorers.scene.Scene
import jp.karakuri.apps.dungeonexplorers.game.scenes.Start
import jp.karakuri.apps.dungeonexplorers.shape.{Screen, Shape}
import jp.karakuri.apps.dungeonexplorers.camera.Camera
import jp.karakuri.apps.dungeonexplorers.Modules._
import scala.collection.mutable.ListBuffer
import jp.karakuri.apps.dungeonexplorers.light.Light
import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, Shader}
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders
import jp.karakuri.apps.dungeonexplorers.texture.{DepthTexture, BufferTexture}
import jp.karakuri.apps.dungeonexplorers.fbo.FrameBufferObject
import org.lwjgl.util.vector.Vector3f
import jp.karakuri.apps.dungeonexplorers.effects.BlurEffect

abstract class Context {
  private val objectLayer = new ObjectLayer(this)
  private val guiLayer = new GuiLayer
  private val scene:Scene = new Start

  private val lights = new ListBuffer[Light]()

  private lazy val colorBuffer = new BufferTexture(640, 480)
  private lazy val normalBuffer = new BufferTexture(640, 480)
  private lazy val depthBuffer = new DepthTexture(640, 480)

  private lazy val aoBuffer = new BufferTexture(160, 120)

  private lazy val fbo = new FrameBufferObject()
  private lazy val aoFbo = new FrameBufferObject()

  private lazy val screen = new Screen(colorBuffer)

  private lazy val blurEffect = new BlurEffect(aoBuffer)

  scene.setContext(this)

  val camera = new Camera

  def init() {
    fbo.setTexture0(colorBuffer)
    fbo.setTexture1(normalBuffer)
    fbo.setDepthTexture(depthBuffer)

    aoFbo.setTexture0(aoBuffer)

    scene.onInit()
    onInit()
  }

  def update() {
    scene.onUpdate()
  }

  def render() {
    preRender()

    fbo.bind()
    fbo.drawBuffers()
    Shaders.use("pass1")
    gl.glEnable(GLConsts.GL_DEPTH_TEST)
    gl.glClear(GLConsts.GL_COLOR_BUFFER_BIT | GLConsts.GL_DEPTH_BUFFER_BIT)
    preObjectRender()
    camera.use()
    lights.foreach(_.update())
    objectLayer.render()
    fbo.unbind()

    // ao start
    gl.glViewport(0, 0, 160, 120)
    aoFbo.bind()
    aoFbo.drawBuffers()
    Shaders.use("ao")
    gl.glActiveTexture(GLConsts.GL_TEXTURE2)
    depthBuffer.bind()
    Shaders.current.setUniform("depthid", 2)

    gl.glDisable(GLConsts.GL_DEPTH_TEST)
    screen.onRender()

    aoFbo.unbind()

    val aoBuffer2 = blurEffect.blur()
    // ao end

    gl.glViewport(0, 0, 640, 480)
    gl.glDrawBuffer(GLConsts.GL_BACK)
    Shaders.use("pass2")
    gl.glActiveTexture(GLConsts.GL_TEXTURE2)
    normalBuffer.bind()
    Shaders.current.setUniform("normalid", 2)
    gl.glActiveTexture(GLConsts.GL_TEXTURE3)
    depthBuffer.bind()
    Shaders.current.setUniform("depthid", 3)
    gl.glActiveTexture(GLConsts.GL_TEXTURE4)
    aoBuffer2.bind()
    Shaders.current.setUniform("aoid", 4)

    gl.glDisable(GLConsts.GL_DEPTH_TEST)
    screen.onRender()

    Shaders.use("gui")
    preGuiRender()
    guiLayer.render()
  }

  def addObject(geometry:Shape) {
    objectLayer.append(geometry)
  }

  def addGui(geometry:Shape) {
    guiLayer.append(geometry)
  }

  def addLight(light:Light) {
    lights += light
  }

  def onInit()
  def preRender()
  def preObjectRender()
  def preGuiRender()
}
