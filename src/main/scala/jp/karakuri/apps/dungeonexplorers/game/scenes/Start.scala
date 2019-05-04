package jp.karakuri.apps.dungeonexplorers.game.scenes

import jp.karakuri.apps.dungeonexplorers.scene.Scene
import jp.karakuri.apps.dungeonexplorers.Modules._
import jp.karakuri.apps.dungeonexplorers.shape.{Window, Shape, Quad, TextBox}
import org.lwjgl.util.vector.Vector3f
import org.lwjgl.input.Keyboard
import jp.karakuri.apps.dungeonexplorers.light.Light

class Start extends Scene {
  val light = new Light(0)

  override def onInit() {
    val font = fontLoader.load("mplus-1mn-bold.ttf")
    val mapTexture = imageLoader.load("textures/map/solid/solid.png")
    val mapBumpTexture = imageLoader.load("textures/map/solid/solid-normal.png")
    val windowTexture = imageLoader.load("textures/window/default.png")

    val map = mapLoader.load("test.csv")
    val gui = guiLoader.load("test.xml")

    map.setTexture(mapTexture)
    map.setBumpTexture(mapBumpTexture)

    gui.setFontTexture(font)
    gui.setWindowTexture(windowTexture)

    map.setModels(Map(
      1 -> modelLoader.load("map/solid/solid-1.dae"),
      2 -> modelLoader.load("map/solid/solid-2.dae"),
      3 -> modelLoader.load("map/solid/solid-3.dae"),
      4 -> modelLoader.load("map/solid/solid-4.dae"),
      5 -> modelLoader.load("map/solid/solid-5.dae"),
      6 -> modelLoader.load("map/solid/solid-6.dae"),
      7 -> modelLoader.load("map/solid/solid-7.dae"),
      8 -> modelLoader.load("map/solid/solid-8.dae"),
      9 -> modelLoader.load("map/solid/solid-9.dae"),
      10 -> modelLoader.load("map/solid/solid-10.dae"),
      11 -> modelLoader.load("map/solid/solid-11.dae"),
      12 -> modelLoader.load("map/solid/solid-12.dae"),
      13 -> modelLoader.load("map/solid/solid-13.dae"),
      14 -> modelLoader.load("map/solid/solid-14.dae"),
      15 -> modelLoader.load("map/solid/solid-15.dae")
    ))

    for (grid <- map.createShapes(500)) {
      addObject(grid)
    }

    for (node <- gui.createShapes()) {
      addGui(node)
    }

    Keyboard.enableRepeatEvents(true)
    addLight(light)

    camera.moveToY(250)
  }

  override def onUpdate() {
    light.setTranslation(camera.translation)
    while(Keyboard.next()) {
      if (Keyboard.getEventKeyState) {
        Keyboard.getEventKey match {
          case Keyboard.KEY_UP => camera.moveToFront(20)
          case Keyboard.KEY_DOWN => camera.moveToBack(20)
          case Keyboard.KEY_LEFT => camera.rotateY(-5)
          case Keyboard.KEY_RIGHT => camera.rotateY(5)
          case _ =>
        }
      }
    }
  }

  override def onFinish() {

  }
}
