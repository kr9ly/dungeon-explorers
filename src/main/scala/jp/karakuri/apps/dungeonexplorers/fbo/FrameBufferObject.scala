package jp.karakuri.apps.dungeonexplorers.fbo

import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import jp.karakuri.apps.dungeonexplorers.texture.Texture
import scala.collection.mutable.ArrayBuffer

class FrameBufferObject(implicit gl:GL) {
  val fboId = gl.glGenFramebuffer()

  val buffers:ArrayBuffer[Int] = ArrayBuffer()

  def setTexture0(texture:Texture) {
    bind()
    buffers += GLConsts.GL_COLOR_ATTACHMENT0.value
    gl.glFramebufferTexture2D(GLConsts.GL_FRAMEBUFFER, GLConsts.GL_COLOR_ATTACHMENT0, GLConsts.GL_TEXTURE_2D, texture.textureId, 0)
    unbind()
  }

  def setTexture1(texture:Texture) {
    bind()
    buffers += GLConsts.GL_COLOR_ATTACHMENT1.value
    gl.glFramebufferTexture2D(GLConsts.GL_FRAMEBUFFER, GLConsts.GL_COLOR_ATTACHMENT1, GLConsts.GL_TEXTURE_2D, texture.textureId, 0)
    unbind()
  }

  def setDepthTexture(texture:Texture) {
    bind()
    gl.glFramebufferTexture2D(GLConsts.GL_FRAMEBUFFER, GLConsts.GL_DEPTH_ATTACHMENT, GLConsts.GL_TEXTURE_2D, texture.textureId, 0)
    unbind()
  }

  def bind() {
    gl.glBindFramebuffer(fboId)
  }

  def unbind() {
    gl.glBindFramebuffer(0)
  }

  def drawBuffers() {
    gl.glDrawBuffers(buffers.toArray)
  }

  def checkStatuses() {
    gl.glCheckFramebufferStatus(GLConsts.GL_FRAMEBUFFER) match {
      case GLConsts.GL_FRAMEBUFFER_COMPLETE.value => println("ok")
      case GLConsts.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT.value => println("fail attachment")
      case GLConsts.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT.value => println("fail attachment")
      case GLConsts.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER.value => println("fail drawbuffer")
      case GLConsts.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER.value => println("fail readbuffer")
      case GLConsts.GL_FRAMEBUFFER_UNSUPPORTED.value => println("unsupported")
      case GLConsts.GL_FRAMEBUFFER_UNDEFINED.value => println("undefined")
      case a => println(a)
    }
  }
}
