package jp.karakuri.apps.dungeonexplorers.dataloader

import jp.karakuri.apps.dungeonexplorers.loader.AssetsLoader
import scala.io.Source
import jp.karakuri.apps.dungeonexplorers.data.map.MapGenerator
import jp.karakuri.apps.dungeonexplorers.gl.GL

class MapLoader(implicit assetsLoader: AssetsLoader, gl:GL) {
  def load(path:String) = {
    val mapData = Source.fromInputStream(assetsLoader.load("data/map/" + path)).getLines().map(_.split(",").map(_.toInt)).toArray

    val width = mapData.maxBy(_.length).length
    val length = mapData.length

    new MapGenerator(width, length, mapData)
  }
}
