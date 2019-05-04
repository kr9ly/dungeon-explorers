package jp.karakuri.apps.dungeonexplorers

// What GL version you plan on using

import org.lwjgl.opengl.{
Display,
DisplayMode
}

import jp.karakuri.apps.dungeonexplorers.platform.PCContext
import org.lwjgl.Sys

object Game extends App {
  val width = 640
  val height = 480
  val depth = 300
  val displayMode = new DisplayMode(640, 480)
  val context = new PCContext
  val fps = 60
  var currentFps = 0
  var lastTime = getTime

  Display.setTitle("LWJGL Test")
  Display.setDisplayMode(displayMode)
  Display.create()

  context.init()
  while (!Display.isCloseRequested) {
    context.update()
    context.render()
    Display.update()
    //Display.sync(fps)

    if (getTime - lastTime > 1000) {
      Display.setTitle("FPS: " + currentFps)
      currentFps = 0
      lastTime += 1000
    }
    currentFps += 1
  }

  Display.destroy()
  sys.exit(0)

  def getTime = (Sys.getTime * 1000) / Sys.getTimerResolution
}
