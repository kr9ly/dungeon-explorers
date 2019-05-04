package jp.karakuri.apps.dungeonexplorers.utils

import org.lwjgl.util.vector.Vector3f
import jp.karakuri.apps.dungeonexplorers.camera.Camera

class FrustumTest(left:Float, right:Float, bottom:Float, top:Float, near:Float, far:Float) {
  val angle = Math.acos(Vector3f.dot(new Vector3f(left, near, 0), new Vector3f(right, near, 0))) * 0.5
  val tang = Math.tan(angle)
  val ratio = (right - left) / (top - bottom)
  val angleX = Math.atan(tang*ratio)
  val sphereFactorY = 1.0 / Math.cos(angle)
  val sphereFactorX = 1.0 / Math.cos(angleX)


  def sphereTest(point:Vector3f, camera:Camera, radius:Float):Boolean = {
    val v = Vector3f.sub(point, camera.translation, null)

    val az = Vector3f.dot(v, camera.direction)
    val ay = Vector3f.dot(v, camera.top)
    val ax = Vector3f.dot(v, camera.left)
    val d = sphereFactorY * radius
    val d2 = sphereFactorX * radius
    val az2 = az * tang
    val az3 = az2 * ratio

    if (az > far + radius || az < near - radius) return false
    // if (az > far - radius || az < near - radius) return false
    if (ay > az + d || ay < -az - d) return false
    // if (ay > az - d || ay < -az + d) return false
    if (ax > az + d || ax < -az - d) return false
    //if (ax > az - d || ax < -az + d) return false

    true
  }
}
