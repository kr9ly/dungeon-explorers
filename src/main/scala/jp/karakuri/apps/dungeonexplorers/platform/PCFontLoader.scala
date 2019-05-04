package jp.karakuri.apps.dungeonexplorers.platform

import jp.karakuri.apps.dungeonexplorers.loader.{AssetsLoader, FontLoader}
import jp.karakuri.apps.dungeonexplorers.gl.{GL}
import java.awt.{Transparency, RenderingHints, Graphics2D, Font}
import java.awt.image.{ComponentColorModel, BufferedImage, DataBuffer, Raster}
import java.util.Hashtable
import java.awt.color.ColorSpace
import jp.karakuri.apps.dungeonexplorers.texture.FontTexture

class PCFontLoader(implicit gl: GL, assetsLoader: AssetsLoader) extends FontLoader {

  private val glAlphaColorModel = new ComponentColorModel(
    ColorSpace.getInstance(ColorSpace.CS_sRGB),
    Array(8, 8, 8, 8),
    true,
    false,
    Transparency.TRANSLUCENT,
    DataBuffer.TYPE_BYTE);

  override def load(path: String): FontTexture = {
    val in = assetsLoader.load(path)
    val font = Font.createFont(Font.TRUETYPE_FONT, in)
    val newFont = font.deriveFont(Font.PLAIN, 24)
    val border = 2
    in.close

    val raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, 1024, 1024, 4, null);
    val texImage = new BufferedImage(glAlphaColorModel, raster, false, new Hashtable())

    val g = texImage.getGraphics.asInstanceOf[Graphics2D]
    g.setFont(newFont)

    val metrics = g.getFontMetrics(newFont)

    val height = metrics.getMaxAscent + 1 + border * 2 + 2
    val maxY = 1024 / height
    val width = metrics.getMaxCharBounds(g).getWidth.toInt + 1 + border * 2 + 2
    val maxX = 1024 / width

    val textureId = gl.glGenTexture()
    new PCFontTexture(textureId, maxX, maxY, width, height, texImage, border, newFont)
  }
}
