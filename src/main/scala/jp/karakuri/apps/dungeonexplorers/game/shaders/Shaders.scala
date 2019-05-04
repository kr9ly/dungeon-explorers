package jp.karakuri.apps.dungeonexplorers.game.shaders

import jp.karakuri.apps.dungeonexplorers.gl.Shader

import jp.karakuri.apps.dungeonexplorers.Modules._

object Shaders {

  private lazy val shaders = Map(
    "pass1" -> new Shader("pass1.vert", "pass1.frag"),
    "pass2" -> new Shader("pass2.vert", "pass2.frag"),
    "ao"    -> new Shader("ao.vert", "ao.frag"),
    "blur"  -> new Shader("blur.vert", "blur.frag"),
    "copy"  -> new Shader("copy.vert", "copy.frag"),
    "gui"   -> new Shader("gui.vert", "gui.frag")
  )

  private var shaderName:String = "pass1"

  def use(key:String) {
    shaderName = key
    shaders(key).use()
  }

  def current = shaders(shaderName)
}
