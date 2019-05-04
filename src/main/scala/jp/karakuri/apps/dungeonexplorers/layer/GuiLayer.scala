package jp.karakuri.apps.dungeonexplorers.layer

import java.util
import jp.karakuri.apps.dungeonexplorers.shape.Shape
import org.lwjgl.util.vector.Vector3f
import java.util.{Comparator, Collections}
import scala.collection.JavaConversions._

class GuiLayer {
  private val geometrys = new util.ArrayList[Shape]()

  def append(geometry: Shape) {
    geometrys.add(geometry)
  }

  def sort(standard: Vector3f) {
    Collections.sort(geometrys, new Comparator[Shape] {
      override def compare(g1: Shape, g2: Shape): Int = (g1.z - g2.z).toInt
    })
  }

  def render() {
    geometrys.foreach(_.onRender())
  }
}
