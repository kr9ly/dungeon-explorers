package jp.karakuri.apps.dungeonexplorers.texture

import jp.karakuri.apps.dungeonexplorers.loader.FontLoader
import scala.collection.mutable.ListBuffer
import java.nio.{ByteBuffer, FloatBuffer}
import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils
import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}

trait FontTexture extends Texture {
  val textureId: Int
  val gl: GL

  protected val letters = new ListBuffer[Char]()
  private var needsUpdate = false

  val maxX: Int
  val maxY: Int

  def appendChar(char: Char) {
    if (!letters.contains(char)) {
      letters += char
      needsUpdate = true
    }
  }

  def appendText(text: String) {
    for (c <- text.toCharArray) {
      appendChar(c)
    }
  }

  def getUVBuffer(text: String): FloatBuffer = BufferUtils.allocateFloatBuffer(text.toCharArray.flatMap(getUVPosition))

  private def getUVPosition(char: Char): Seq[Float] = {
    val i = letters.indexOf(char)
    val y = i / maxX
    val x = i % maxX

    Seq(
      x.toFloat / maxX * widthRate, y.toFloat / maxY * heightRate,
      x.toFloat / maxX * widthRate, (y + 1).toFloat / maxY * heightRate,
      (x + 1).toFloat / maxX * widthRate, (y + 1).toFloat / maxY * heightRate,
      (x + 1).toFloat / maxX * widthRate, y.toFloat / maxY * heightRate
    )
  }

  def update {
    if (needsUpdate) {
      updateBuffer()
      needsUpdate = false
    }
  }

  def updateBuffer() {
    val buffer = getBuffer()

    val pixelFormat = GLConsts.GL_RGBA

    bind()

    gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_MIN_FILTER, GLConsts.GL_LINEAR)
    gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_MAG_FILTER, GLConsts.GL_LINEAR)

    gl.glEnable(GLConsts.GL_TEXTURE_2D)
    gl.glTexImage2D(GLConsts.GL_TEXTURE_2D, 0, pixelFormat, textureWidth, textureHeight, 0, pixelFormat, buffer)
    gl.glDisable(GLConsts.GL_TEXTURE_2D)

    unbind()
  }

  def getBuffer(): ByteBuffer
}
