package jp.karakuri.apps.dungeonexplorers.texture

import jp.karakuri.apps.dungeonexplorers.loader.ImageLoader
import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import jp.karakuri.apps.dungeonexplorers.interface.Disposable

trait Texture extends Disposable {
  val textureId: Int
  val gl: GL
  val width: Int
  val height: Int
  val textureWidth: Int
  val textureHeight: Int

  def bind() {
    gl.glBindTexture(GLConsts.GL_TEXTURE_2D, textureId)
  }

  def unbind() {
    gl.glBindTexture(GLConsts.GL_TEXTURE_2D, 0)
  }

  def dispose() {
    gl.glDeleteTexture(textureId)
  }

  def widthRate = width.toFloat / textureWidth.toFloat

  def heightRate = height.toFloat / textureHeight.toFloat
}
