package jp.karakuri.apps.dungeonexplorers.effects

import jp.karakuri.apps.dungeonexplorers.texture.{BufferTexture, Texture}
import jp.karakuri.apps.dungeonexplorers.fbo.FrameBufferObject
import jp.karakuri.apps.dungeonexplorers.shape.Screen
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders
import jp.karakuri.apps.dungeonexplorers.Modules._

class BlurEffect(src:Texture) {
  val fbo1 = new FrameBufferObject()
  val fbo2 = new FrameBufferObject()
  val fbo3 = new FrameBufferObject()

  val dest1 = new BufferTexture(src.textureWidth * 4 / 5, src.textureHeight * 4 / 5)
  val dest2 = new BufferTexture(dest1.textureWidth * 98 / 100, dest1.textureHeight * 98 / 100)
  val dest3 = new BufferTexture(dest2.textureWidth * 98 / 100, dest2.textureHeight * 98 / 100)

  val shape1 = new Screen(src)
  val shape2 = new Screen(src)
  val shape3 = new Screen(src)

  fbo1.setTexture0(dest1)
  fbo2.setTexture0(dest2)
  fbo3.setTexture0(dest3)

  def blur() = {
    Shaders.use("copy")
    gl.glViewport(0, 0, dest1.textureWidth, dest1.textureHeight)
    fbo1.bind()
    fbo1.drawBuffers()
    shape1.onRender()
    fbo1.unbind()

    Shaders.use("blur")
    Shaders.current.setUniform("texwidth", dest2.textureWidth)
    Shaders.current.setUniform("texheight", dest2.textureHeight)
    gl.glViewport(0, 0, dest2.textureWidth, dest2.textureHeight)
    fbo2.bind()
    fbo2.drawBuffers()
    shape2.onRender()
    fbo2.unbind()

    Shaders.current.setUniform("texwidth", dest3.textureWidth)
    Shaders.current.setUniform("texheight", dest3.textureHeight)
    gl.glViewport(0, 0, dest3.textureWidth, dest3.textureHeight)
    fbo3.bind()
    fbo3.drawBuffers()
    shape3.onRender()
    fbo3.unbind()

    dest3
  }
}
