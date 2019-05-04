package jp.karakuri.apps.dungeonexplorers

import jp.karakuri.apps.dungeonexplorers.platform._
import jp.karakuri.apps.dungeonexplorers.loader.ModelLoader
import jp.karakuri.apps.dungeonexplorers.dataloader.{GuiLoader, MapLoader}

object Modules {
  implicit val gl = new PCGL()
  implicit val assetsLoader = new PCAssetsLoader()
  implicit val imageLoader = new PCImageLoader()
  implicit val fontLoader = new PCFontLoader()
  implicit val modelLoader = new ModelLoader()
  implicit val mapLoader = new MapLoader()
  implicit val guiLoader = new GuiLoader()
}
