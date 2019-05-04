package jp.karakuri.apps.dungeonexplorers.light

import jp.karakuri.apps.dungeonexplorers.interface.Geometry
import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import jp.karakuri.apps.dungeonexplorers.Modules._
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders

class Light(index:Int)(implicit gl:GL) extends Geometry {
  private var r = 1f
  private var g = 1f
  private var b = 1f

  private var power = 2000f

  def setColor(r:Float, g:Float, b:Float) {
    this.r = r
    this.g = g
    this.b = b
  }

  def update() {
    Shaders.current.setUniform("lights[" + index + "].position", x, y, z)
    Shaders.current.setUniform("lights[" + index + "].diffuse", r, g, b)
    Shaders.current.setUniform("lights[" + index + "].power", power)
  }
}
