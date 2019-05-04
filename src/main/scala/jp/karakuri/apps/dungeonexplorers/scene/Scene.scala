package jp.karakuri.apps.dungeonexplorers.scene

import jp.karakuri.apps.dungeonexplorers.context.Context
import jp.karakuri.apps.dungeonexplorers.shape.Shape
import jp.karakuri.apps.dungeonexplorers.light.Light

abstract class Scene {
  private var context:Context = null

  def setContext(context:Context) {
    this.context = context
  }

  def onInit()

  def onUpdate()

  def onFinish()

  def addObject(geometry:Shape) {
    context.addObject(geometry)
  }

  def addGui(geometry:Shape) {
    context.addGui(geometry)
  }

  def addLight(light:Light) {
    context.addLight(light)
  }

  def camera = context.camera
}
