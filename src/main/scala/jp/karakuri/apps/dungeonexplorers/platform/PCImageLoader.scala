package jp.karakuri.apps.dungeonexplorers.platform

import jp.karakuri.apps.dungeonexplorers.gl._
import javax.imageio.ImageIO
import java.awt.image._
import java.awt.color.ColorSpace
import java.awt._
import java.util.Hashtable
import java.nio.{ByteOrder, ByteBuffer}
import jp.karakuri.apps.dungeonexplorers.loader.{ImageLoader, AssetsLoader}
import jp.karakuri.apps.dungeonexplorers.texture.Texture

class PCImageLoader(implicit gl: GL, assetsLoader: AssetsLoader) extends ImageLoader {

  private val glAlphaColorModel = new ComponentColorModel(
    ColorSpace.getInstance(ColorSpace.CS_sRGB),
    Array(8, 8, 8, 8),
    true,
    false,
    Transparency.TRANSLUCENT,
    DataBuffer.TYPE_BYTE);
  private val glColorModel = new ComponentColorModel(
    ColorSpace.getInstance(ColorSpace.CS_sRGB),
    Array(8, 8, 8, 8),
    false,
    false,
    Transparency.OPAQUE,
    DataBuffer.TYPE_BYTE);

  def load(path: String): Texture = {
    val in = assetsLoader.load(path)
    val image = ImageIO.read(in)

    in.close()

    val width = image.getWidth
    val height = image.getHeight

    val texWidth: Int = Math.pow(2, Math.ceil(Math.log(width) / Math.log(2))).toInt
    val texHeight: Int = Math.pow(2, Math.ceil(Math.log(height) / Math.log(2))).toInt

    val hasAlpha = image.getColorModel.hasAlpha

    val texImage = if (hasAlpha) {
      val raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null);
      new BufferedImage(glAlphaColorModel, raster, false, new Hashtable())
    } else {
      val raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, texWidth, texHeight, 3, null);
      new BufferedImage(glColorModel, raster, false, new Hashtable())
    }

    val g = texImage.getGraphics
    g.setColor(new Color(0f, 0f, 0f, 0f))
    g.fillRect(0, 0, texWidth, texHeight)
    g.drawImage(image, 0, 0, null)
    g.dispose()

    image.flush()

    val data = texImage.getRaster.getDataBuffer.asInstanceOf[DataBufferByte].getData
    texImage.flush()

    val buffer = ByteBuffer.allocateDirect(data.length)
    buffer.order(ByteOrder.nativeOrder)
    buffer.put(data, 0, data.length)
    buffer.flip()

    val pixelFormat = if (hasAlpha) GLConsts.GL_RGBA else GLConsts.GL_RGB
    val textureId = gl.glGenTexture()

    gl.glBindTexture(GLConsts.GL_TEXTURE_2D, textureId)
    gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_MIN_FILTER, GLConsts.GL_LINEAR)
    gl.glTexParameter(GLConsts.GL_TEXTURE_2D, GLConsts.GL_TEXTURE_MAG_FILTER, GLConsts.GL_LINEAR)

    gl.glEnable(GLConsts.GL_TEXTURE_2D)
    gl.glTexImage2D(GLConsts.GL_TEXTURE_2D, 0, pixelFormat, texWidth, texHeight, 0, pixelFormat, buffer)
    gl.glDisable(GLConsts.GL_TEXTURE_2D)

    buffer.clear()

    new PCImageTexture(textureId, width, height, texWidth, texHeight)
  }
}
