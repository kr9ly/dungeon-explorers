package jp.karakuri.apps.dungeonexplorers.layer

import jp.karakuri.apps.dungeonexplorers.shape.Shape
import org.lwjgl.util.vector.Vector3f
import java.util
import java.util.{Comparator, Collections}
import scala.collection.JavaConversions._
import jp.karakuri.apps.dungeonexplorers.context.Context
import jp.karakuri.apps.dungeonexplorers.utils.{FrustumTest, MatrixUtils}

class ObjectLayer(context:Context) {
  private val geometrys = new util.ArrayList[Shape]()
  private val overlayGeometrys = new util.ArrayList[Shape]()

  val test = new FrustumTest(-1, 1, -0.75f, 0.75f, 1, 3000)

  def append(geometry: Shape) {
    if (geometry.depthMask) {
      geometrys.add(geometry)
    } else {
      overlayGeometrys.add(geometry)
    }
  }

  def sort(standard: Vector3f) {
    Collections.sort(overlayGeometrys, new Comparator[Shape] {
      override def compare(g1: Shape, g2: Shape): Int = {
        (calcDistance(g1.translation, standard) - calcDistance(g2.translation, standard)).toInt
      }
    })
  }

  def render() {
    sort(context.camera.translation)

    geometrys.filter(g => calcDistance(g.translation, context.camera.translation) < 2500)
             .filter(g => test.sphereTest(g.translation, context.camera, g.model.radius * g.getScale.length))
             .foreach(_.onRender())
    overlayGeometrys.foreach(_.onRender())
  }

  private def calcDistance(v1: Vector3f, v2: Vector3f) = Vector3f.sub(v1, v2, null).length()
}
