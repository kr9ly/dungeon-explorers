package jp.karakuri.apps.dungeonexplorers.dataloader

import jp.karakuri.apps.dungeonexplorers.loader.AssetsLoader
import jp.karakuri.apps.dungeonexplorers.gl.GL
import scala.io.Source
import jp.karakuri.apps.dungeonexplorers.data.gui.GuiGenerator

class GuiLoader(implicit assetsLoader: AssetsLoader, gl:GL) {
  def load(path:String) = {
    val str = Source.fromInputStream(assetsLoader.load("data/gui/" + path), "UTF-8").mkString("")
    new GuiGenerator(str)
  }
}
