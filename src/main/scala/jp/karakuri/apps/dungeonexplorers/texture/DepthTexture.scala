package jp.karakuri.apps.dungeonexplorers.texture

import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils
import java.nio.{IntBuffer, FloatBuffer, ByteBuffer}

class DepthTexture(val width:Int, val height:Int)(implicit val gl:GL) extends Texture {
  val textureId = gl.glGenTexture()

  val textureWidth = width
  val textureHeight = height

  bind()

  gl.glTexImage2D(GLConsts.GL_TEXTURE_2D, 0, GLConsts.GL_DEPTH_COMPONENT24, width, height, 0, GLConsts.GL_DEPTH_COMPONENT, null.asInstanceOf[IntBuffer])
  gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_WRAP_S, GLConsts.GL_CLAMP_TO_EDGE)
  gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_WRAP_T, GLConsts.GL_CLAMP_TO_EDGE)
  gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_MIN_FILTER, GLConsts.GL_LINEAR)
  gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_MAG_FILTER, GLConsts.GL_LINEAR)

  unbind()
}
