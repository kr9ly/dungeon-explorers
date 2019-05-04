package jp.karakuri.apps.dungeonexplorers.shape

import jp.karakuri.apps.dungeonexplorers.texture.Texture
import jp.karakuri.apps.dungeonexplorers.Modules._
import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils
import org.lwjgl.util.vector.Vector3f

class Window(texture: Texture, width: Int, height: Int) extends Shape(texture) {
  val partsWidth = texture.width / 3f
  val partsHeight = texture.height / 3f
  val centerWidth = width - partsWidth * 2
  val centerHeight = height - partsHeight * 2

  val vert = BufferUtils.allocateFloatBuffer(Array(
    0f          , 0f            , 0,
    0f          , -partsHeight  , 0,
    partsWidth  , -partsHeight  , 0,
    partsWidth  , 0f            , 0,

    partsWidth + 0f           , 0f            , 0,
    partsWidth + 0f           , -partsHeight  , 0,
    partsWidth + centerWidth  , -partsHeight  , 0,
    partsWidth + centerWidth  , 0f            , 0,

    partsWidth + centerWidth + 0f         , 0f            , 0,
    partsWidth + centerWidth + 0f         , -partsHeight  , 0,
    partsWidth + centerWidth + partsWidth , -partsHeight  , 0,
    partsWidth + centerWidth + partsWidth , 0f            , 0,

    0f          , -partsHeight + 0f            , 0,
    0f          , -partsHeight - centerHeight  , 0,
    partsWidth  , -partsHeight - centerHeight  , 0,
    partsWidth  , -partsHeight + 0f            , 0,

    partsWidth + 0f           , -partsHeight + 0f            , 0,
    partsWidth + 0f           , -partsHeight - centerHeight  , 0,
    partsWidth + centerWidth  , -partsHeight - centerHeight  , 0,
    partsWidth + centerWidth  , -partsHeight + 0f            , 0,

    partsWidth + centerWidth + 0f         , -partsHeight + 0f            , 0,
    partsWidth + centerWidth + 0f         , -partsHeight - centerHeight  , 0,
    partsWidth + centerWidth + partsWidth , -partsHeight - centerHeight  , 0,
    partsWidth + centerWidth + partsWidth , -partsHeight + 0f            , 0,

    0f          , -partsHeight - centerHeight + 0f            , 0,
    0f          , -partsHeight - centerHeight - partsHeight  , 0,
    partsWidth  , -partsHeight - centerHeight - partsHeight  , 0,
    partsWidth  , -partsHeight - centerHeight + 0f            , 0,

    partsWidth + 0f           , -partsHeight - centerHeight + 0f            , 0,
    partsWidth + 0f           , -partsHeight - centerHeight - partsHeight  , 0,
    partsWidth + centerWidth  , -partsHeight - centerHeight - partsHeight  , 0,
    partsWidth + centerWidth  , -partsHeight - centerHeight + 0f            , 0,

    partsWidth + centerWidth + 0f         , -partsHeight - centerHeight + 0f            , 0,
    partsWidth + centerWidth + 0f         , -partsHeight - centerHeight - partsHeight  , 0,
    partsWidth + centerWidth + partsWidth , -partsHeight - centerHeight - partsHeight  , 0,
    partsWidth + centerWidth + partsWidth , -partsHeight - centerHeight + 0f            , 0
  ))

  val normal = BufferUtils.allocateFloatBuffer(Array(
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,

    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f
  ))

  val color = BufferUtils.allocateFloatBuffer(Array(
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,

    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f
  ))

  val unit = 1.0f / 3
  val uv = BufferUtils.allocateFloatBuffer(Array(
    0.0f, 0.0f,
    0.0f, unit,
    unit, unit,
    unit, 0.0f,

    unit + 0.0f, 0.0f,
    unit + 0.0f, unit,
    unit + unit, unit,
    unit + unit, 0.0f,

    unit * 2 + 0.0f, 0.0f,
    unit * 2 + 0.0f, unit,
    unit * 2 + unit, unit,
    unit * 2 + unit, 0.0f,

    0.0f, unit + 0.0f,
    0.0f, unit + unit,
    unit, unit + unit,
    unit, unit + 0.0f,

    unit + 0.0f, unit + 0.0f,
    unit + 0.0f, unit + unit,
    unit + unit, unit + unit,
    unit + unit, unit + 0.0f,

    unit * 2 + 0.0f, unit + 0.0f,
    unit * 2 + 0.0f, unit + unit,
    unit * 2 + unit, unit + unit,
    unit * 2 + unit, unit + 0.0f,

    0.0f, unit * 2 + 0.0f,
    0.0f, unit * 2 + unit,
    unit, unit * 2 + unit,
    unit, unit * 2 + 0.0f,

    unit + 0.0f, unit * 2 + 0.0f,
    unit + 0.0f, unit * 2 + unit,
    unit + unit, unit * 2 + unit,
    unit + unit, unit * 2 + 0.0f,

    unit * 2 + 0.0f, unit * 2 + 0.0f,
    unit * 2 + 0.0f, unit * 2 + unit,
    unit * 2 + unit, unit * 2 + unit,
    unit * 2 + unit, unit * 2 + 0.0f


  ))
  val indices = BufferUtils.allocateIntBuffer(Array(
    0, 1, 2,
    0, 2, 3,

    4 + 0, 4 + 1, 4 + 2,
    4 + 0, 4 + 2, 4 + 3,

    4 * 2 + 0, 4 * 2 + 1, 4 * 2 + 2,
    4 * 2 + 0, 4 * 2 + 2, 4 * 2 + 3,

    4 * 3 + 0, 4 * 3 + 1, 4 * 3 + 2,
    4 * 3 + 0, 4 * 3 + 2, 4 * 3 + 3,

    4 * 4 + 0, 4 * 4 + 1, 4 * 4 + 2,
    4 * 4 + 0, 4 * 4 + 2, 4 * 4 + 3,

    4 * 5 + 0, 4 * 5 + 1, 4 * 5 + 2,
    4 * 5 + 0, 4 * 5 + 2, 4 * 5 + 3,

    4 * 6 + 0, 4 * 6 + 1, 4 * 6 + 2,
    4 * 6 + 0, 4 * 6 + 2, 4 * 6 + 3,

    4 * 7 + 0, 4 * 7 + 1, 4 * 7 + 2,
    4 * 7 + 0, 4 * 7 + 2, 4 * 7 + 3,

    4 * 8 + 0, 4 * 8 + 1, 4 * 8 + 2,
    4 * 8 + 0, 4 * 8 + 2, 4 * 8 + 3
  ))

  model.setVertex(vert)
  model.setNormal(normal)
  model.setColor(color)
  model.setUV(uv)
  model.setIndices(indices)
  model.setTangents(BufferUtils.computeTangents(indices, vert, uv))
  model.radius = new Vector3f(0.5f, 0.5f , 0f).length()
}
