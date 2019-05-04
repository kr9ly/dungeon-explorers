package jp.karakuri.apps.dungeonexplorers.model

import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import java.nio.{IntBuffer, FloatBuffer}
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders

class Model(implicit gl:GL) {
  val vertex_id = gl.glGenBuffer()
  val normal_id = gl.glGenBuffer()
  val color_id = gl.glGenBuffer()
  val uv_id = gl.glGenBuffer()
  val indices_id = gl.glGenBuffer()
  val tangent_id = gl.glGenBuffer()
  var vertices = 0
  var radius = 0f

  def setVertex(buffer: FloatBuffer) {
    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, vertex_id)
    buffer.position(0)
    gl.glBufferData(GLConsts.GL_ARRAY_BUFFER, buffer, GLConsts.GL_STATIC_DRAW)
  }

  def setNormal(buffer: FloatBuffer) {
    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, normal_id)
    buffer.position(0)
    gl.glBufferData(GLConsts.GL_ARRAY_BUFFER, buffer, GLConsts.GL_STATIC_DRAW)
  }

  def setColor(buffer: FloatBuffer) {
    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, color_id)
    buffer.position(0)
    gl.glBufferData(GLConsts.GL_ARRAY_BUFFER, buffer, GLConsts.GL_STATIC_DRAW)
  }

  def setUV(buffer: FloatBuffer) {
    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, uv_id)
    buffer.position(0)
    gl.glBufferData(GLConsts.GL_ARRAY_BUFFER, buffer, GLConsts.GL_STATIC_DRAW)
  }

  def setIndices(buffer: IntBuffer) {
    vertices = buffer.limit
    gl.glBindBuffer(GLConsts.GL_ELEMENT_ARRAY_BUFFER, indices_id)
    buffer.position(0)
    gl.glBufferData(GLConsts.GL_ELEMENT_ARRAY_BUFFER, buffer, GLConsts.GL_STATIC_DRAW)
  }

  def setTangents(buffer: FloatBuffer) {
    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, tangent_id)
    buffer.position(0)
    gl.glBufferData(GLConsts.GL_ARRAY_BUFFER, buffer, GLConsts.GL_STATIC_DRAW)
  }

  def bind() {
    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, vertex_id)
    Shaders.current.setAttribute("position", 3, GLConsts.GL_FLOAT, false, 0, 0)

    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, normal_id)
    Shaders.current.setAttribute("normal", 3, GLConsts.GL_FLOAT, false, 0, 0)

    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, color_id)
    Shaders.current.setAttribute("color", 4, GLConsts.GL_FLOAT, false, 0, 0)

    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, uv_id)
    Shaders.current.setAttribute("uv", 2, GLConsts.GL_FLOAT, false, 0, 0)

    gl.glBindBuffer(GLConsts.GL_ARRAY_BUFFER, tangent_id)
    Shaders.current.setAttribute("tangent", 3, GLConsts.GL_FLOAT, false, 0, 0)
  }
}
