package jp.karakuri.apps.dungeonexplorers.platform

import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import java.awt.image.{DataBufferByte, BufferedImage}
import java.awt.{Graphics2D, RenderingHints, Color, Font}
import java.nio.{ByteOrder, ByteBuffer}
import jp.karakuri.apps.dungeonexplorers.texture.FontTexture

class PCFontTexture(val textureId: Int,
                    val maxX: Int,
                    val maxY: Int,
                    fontWidth: Int,
                    fontHeight: Int,
                    texImage: BufferedImage,
                    border: Int,
                    font: Font)(implicit val gl: GL) extends FontTexture {

  val width = fontWidth * maxX
  val height = fontWidth * maxX
  val textureWidth = 1024
  val textureHeight = 1024

  def getBuffer(): ByteBuffer = {
    val g = texImage.getGraphics.asInstanceOf[Graphics2D]
    g.setFont(font)
    g.setColor(new Color(0f, 0f, 0f, 0f))
    g.fillRect(0, 0, texImage.getWidth, texImage.getHeight)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    for (i <- letters.indices) {
      val x = i % maxX
      val y = i / maxX
      if (border > 0) {
        g.setColor(new Color(0f, 0f, 0f, 1f))

        for (borderX <- Range(0, border * 2 + 1); borderY <- Range(0, border * 2 + 1)) {
          g.drawString(letters(i).toString, x * fontWidth + borderX, (y + 1) * fontHeight - g.getFontMetrics(font).getDescent + borderY)
        }
      }
      g.setColor(new Color(1f, 1f, 1f, 1f))
      g.drawString(letters(i).toString, x * fontWidth + border, (y + 1) * fontHeight - g.getFontMetrics(font).getDescent + border)
    }
    g.dispose()

    val data = texImage.getRaster.getDataBuffer.asInstanceOf[DataBufferByte].getData
    val buffer = ByteBuffer.allocateDirect(data.length)
    buffer.order(ByteOrder.nativeOrder)
    buffer.put(data, 0, data.length)
    buffer.flip()

    buffer
  }
}
