package jp.karakuri.apps.dungeonexplorers.shape

import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils
import jp.karakuri.apps.dungeonexplorers.Predef._
import jp.karakuri.apps.dungeonexplorers.Modules._
import jp.karakuri.apps.dungeonexplorers.texture.FontTexture
import org.lwjgl.util.vector.Vector3f

class TextBox(texture: FontTexture, text: String, fontSize: Int, color: (Float, Float, Float)) extends Shape(texture) {
  setText(text)

  def setText(text: String) {
    texture.appendText(text)
    texture.update

    val textWithoutNl = text.replace("\n", "")
    val vert = BufferUtils.allocateFloatBuffer(textWithoutNl.length * 3 * 4)
    var x = 0f
    var y = 1f
    for (c <- text.toCharArray) {
      if (c == '\n') {
        x = 0
        y += 1
      } else {
        vert.put(Array(
          x + 0f, -y + 1f, 0,
          x + 0f, -y + 0f, 0,
          x + 1f, -y + 0f, 0,
          x + 1f, -y + 1f, 0
        ))
        if (isHalfLetter(c)) {
          x += 0.5f
        } else {
          x += 1f
        }
      }
    }

    val normal = BufferUtils.allocateFloatBuffer(textWithoutNl.toCharArray.flatMap(_ => Seq(0.0f, 0.0f, 1.0f)))
    val col = BufferUtils.allocateFloatBuffer(textWithoutNl.length * 4 * 4)
    for (i <- Range(0, textWithoutNl.length * 4)) {
      col.put(color._1).put(color._2).put(color._3).put(1)
    }
    val uv = texture.getUVBuffer(textWithoutNl)
    val indices = BufferUtils.allocateIntBuffer(Range(0, textWithoutNl.length * 4, 4).flatMap(i => Seq(
      i + 0, i + 1, i + 2,
      i + 0, i + 2, i + 3
    )).toArray)

    model.setVertex(vert)
    model.setNormal(normal)
    model.setColor(col)
    model.setUV(uv)
    model.setIndices(indices)
    model.setTangents(BufferUtils.computeTangents(indices, vert, uv))

    setScale(new Vector3f(fontSize, fontSize, 0))
  }
}
