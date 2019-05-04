package jp.karakuri.apps.dungeonexplorers.gl

import jp.karakuri.apps.dungeonexplorers.Modules._
import jp.karakuri.apps.dungeonexplorers.loader.AssetsLoader
import scala.io.Source
import java.nio.FloatBuffer

class Shader(vertPath:String, fragPath:String)(implicit assets:AssetsLoader, gl:GL) {
  val vertShader = gl.glCreateVertexShader()
  val fragShader = gl.glCreateFragmentShader()

  val vertSource = Source.fromInputStream(assets.load("shaders/" + vertPath)).mkString("")
  val fragSource = Source.fromInputStream(assets.load("shaders/" + fragPath)).mkString("")

  gl.glCompileShader(vertShader, vertSource)
  gl.glCompileShader(fragShader, fragSource)

  val programId = gl.glCreateProgram(vertShader, fragShader)
  gl.glLinkProgram(programId)

  private val uniformLocations = scala.collection.mutable.Map[String, Int]()
  private val attributeLocations = scala.collection.mutable.Map[String, Int]()

  private def getUniformLocation(name:String) = uniformLocations.getOrElseUpdate(name, gl.glGetUniformLocation(programId, name))

  private def getAttribLocation(name:String) = attributeLocations.getOrElse(name, {
    gl.glEnableVertexAttribArray(gl.glGetAttribLocation(programId, name))
    gl.glGetAttribLocation(programId, name)
  })

  def use() {
    gl.glUseProgram(programId)
  }

  def setUniform(name:String, value:Int) {
    gl.glUniform(getUniformLocation(name), value)
  }

  def setUniform(name:String, value:Float) {
    gl.glUniform(getUniformLocation(name), value)
  }

  def setUniform(name:String, value:FloatBuffer) {
    gl.glUniform(getUniformLocation(name), value)
  }

  def setUniform(name:String, x:Float, y:Float, z:Float) {
    gl.glUniform(getUniformLocation(name), x, y, z)
  }

  def setAttribute(name:String, size: Int, type_ : GLConsts, normalized:Boolean, stride: Int, offset: Long) {
    gl.glVertexAttribPointer(getAttribLocation(name), size, type_, normalized, stride, offset)
  }
}
