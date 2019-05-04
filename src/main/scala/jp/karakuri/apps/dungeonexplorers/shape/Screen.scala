package jp.karakuri.apps.dungeonexplorers.shape

import jp.karakuri.apps.dungeonexplorers.texture.Texture
import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils

import jp.karakuri.apps.dungeonexplorers.Modules._

class Screen(texture: Texture) extends Shape(texture) {
  val vert = BufferUtils.allocateFloatBuffer(Array(
    -1f, 1f, 0,
    -1f, -1f, 0,
    1f, -1f, 0,
    1f, 1f, 0
  ))

  val normal = BufferUtils.allocateFloatBuffer(Array(
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f,
    0.0f, 0.0f, 1.0f
  ))

  val color = BufferUtils.allocateFloatBuffer(Array(
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f,
    1.0f, 1.0f, 1.0f, 1.0f
  ))

  val uv = BufferUtils.allocateFloatBuffer(Array(
    0.0f, 0.0f,
    0.0f, texture.heightRate,
    texture.widthRate, texture.heightRate,
    texture.widthRate, 0.0f
  ))
  val indices = BufferUtils.allocateIntBuffer(Array(
    0, 1, 2,
    0, 2, 3
  ))

  model.setVertex(vert)
  model.setNormal(normal)
  model.setColor(color)
  model.setUV(uv)
  model.setIndices(indices)
  model.setTangents(BufferUtils.computeTangents(indices, vert, uv))
}
