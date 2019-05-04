package jp.karakuri.apps.dungeonexplorers.shape

import java.nio.{IntBuffer, FloatBuffer}
import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import jp.karakuri.apps.dungeonexplorers.Modules._
import org.lwjgl.util.vector.{Vector2f, Matrix4f, Vector3f}
import jp.karakuri.apps.dungeonexplorers.texture.Texture
import jp.karakuri.apps.dungeonexplorers.interface.{Geometry, Disposable}
import scala.collection.mutable.ListBuffer
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders
import jp.karakuri.apps.dungeonexplorers.utils.{VectorUtils, MatrixUtils, BufferUtils}
import jp.karakuri.apps.dungeonexplorers.model.Model

class Shape(val texture: Texture, val model: Model = new Model, val depthMask:Boolean = true)(implicit gl: GL) extends Geometry {
  private val childs = ListBuffer[Shape]()
  private var bumpTexture:Texture = null

  def setBumpTexture(texture: Texture) {
    bumpTexture = texture
  }

  def appendChild(shape:Shape) {
    childs += shape
  }

  private def render() {
    Shaders.current.setUniform("modelPos", x, y, z)
    Shaders.current.setUniform("modelRot", getRotateMatrix)
    val scale = new Matrix4f()
    scale.scale(new Vector3f(scaleX, scaleY, scaleZ))
    Shaders.current.setUniform("modelScale", MatrixUtils.matrixToFloatBuffer(scale))

    /* Model */
    model.bind()

    /* Texture */
    gl.glActiveTexture(GLConsts.GL_TEXTURE0)
    texture.bind()
    Shaders.current.setUniform("texid", 0)

    /* Bump Map */
    if (bumpTexture != null) {
      gl.glActiveTexture(GLConsts.GL_TEXTURE1)
      bumpTexture.bind()
      Shaders.current.setUniform("bumpid", 1)
      Shaders.current.setUniform("useBumpmap", 1)
    }

    /* Alpha Blend */
    gl.glEnable(GLConsts.GL_BLEND)
    gl.glBlendFunc(GLConsts.GL_SRC_ALPHA, GLConsts.GL_ONE_MINUS_SRC_ALPHA)

    /* Alpha Test */
    gl.glEnable(GLConsts.GL_ALPHA_TEST)
    gl.glAlphaFunc(GLConsts.GL_GREATER, 0f)

    /* Depth Mask */
    gl.glDepthMask(depthMask)

    /* Indices */
    gl.glBindBuffer(GLConsts.GL_ELEMENT_ARRAY_BUFFER, model.indices_id)
    gl.glDrawElements(GLConsts.GL_TRIANGLES, model.vertices, 0)

    gl.glDepthMask(true)

    gl.glDisable(GLConsts.GL_ALPHA_TEST)
    gl.glDisable(GLConsts.GL_BLEND)

    if (bumpTexture != null) {
      Shaders.current.setUniform("useBumpmap", 0)
    }

    childs.foreach(_.onRender())
  }

  def onRender() {
    render()
  }

}