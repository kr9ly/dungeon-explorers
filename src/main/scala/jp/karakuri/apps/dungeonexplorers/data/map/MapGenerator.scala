package jp.karakuri.apps.dungeonexplorers.data.map

import jp.karakuri.apps.dungeonexplorers.model.Model
import jp.karakuri.apps.dungeonexplorers.texture.Texture
import jp.karakuri.apps.dungeonexplorers.shape.Shape
import org.lwjgl.util.vector.Vector3f
import jp.karakuri.apps.dungeonexplorers.gl.GL

class MapGenerator(val width:Int, height:Int, val layer0:Array[Array[Int]])(implicit gl:GL) {
  private var models = Map[Int,Model]()
  private var texture:Texture = null
  private var bumpTexture:Texture = null

  def setTexture(texture:Texture) {
    this.texture = texture
  }

  def setBumpTexture(texture:Texture) {
    this.bumpTexture = texture
  }

  def setModels(models: Map[Int,Model]) {
    this.models = models
  }

  def createShapes(gridSize:Int) = {
    for (z <- Range(0, height); x <- Range(0, width)) yield {
      val shape = new Shape(texture, models(layer0(z)(x)))
      shape.setBumpTexture(bumpTexture)
      shape.setScale(new Vector3f(gridSize / 2 * 2f, gridSize / 2, gridSize / 2 * 2f))
      shape.moveToX(x * gridSize * 2f)
      shape.moveToZ(z * gridSize * 2f)

      shape
    }
  }
}
