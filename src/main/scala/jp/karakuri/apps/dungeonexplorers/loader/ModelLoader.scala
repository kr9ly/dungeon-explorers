package jp.karakuri.apps.dungeonexplorers.loader

import jp.karakuri.apps.dungeonexplorers.Modules._
import scala.io.Source
import jp.karakuri.apps.dungeonexplorers.model.ColladaModel

class ModelLoader(implicit assetsLoader: AssetsLoader) {
  def load(path:String) = {
    val str = Source.fromInputStream(assetsLoader.load("meshes/" + path)).mkString("")
    new ColladaModel(str)
  }
}
