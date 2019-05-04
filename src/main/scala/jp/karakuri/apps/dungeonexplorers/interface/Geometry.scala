package jp.karakuri.apps.dungeonexplorers.interface

import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils
import org.lwjgl.util.vector._

trait Geometry {
  private var trans = new Vector3f(0, 0, 0)
  private var rot = new Matrix4f
  private var scale = new Vector3f(1, 1, 1)
  private var r:Quaternion = null

  rot.setIdentity()

  initQuaternion()

  private def initQuaternion() {
    r = new Quaternion()
    rot = quaternionToMatrix(r)
  }

  def rotateByQuaternion(axis: Vector4f) {
    val q = new Quaternion()
    q.setFromAxisAngle(axis)
    q.normalise()
    Quaternion.mul(r, q, r)
    rot = quaternionToMatrix(r)
  }

  def rotateX(degree:Float) {
    rotateByQuaternion(new Vector4f(1, 0, 0,degreeToRadian(degree)))
  }

  def rotateY(degree:Float) {
    rotateByQuaternion(new Vector4f(0, 1, 0,degreeToRadian(degree)))
  }

  def rotateZ(degree:Float) {
    rotateByQuaternion(new Vector4f(0, 0, 1,degreeToRadian(degree)))
  }

  def moveToX(distance:Float) {
    trans = trans.translate(distance, 0, 0)
  }

  def moveToY(distance:Float) {
    trans = trans.translate(0, distance, 0)
  }

  def moveToZ(distance:Float) {
    trans = trans.translate(0, 0, distance)
  }

  def moveToFront(distance:Float) {
    trans = trans.translate(direction.getX * distance, direction.getY * distance, direction.getZ * distance)
  }

  def moveToBack(distance:Float) {
    trans = trans.translate(-direction.getX * distance, -direction.getY * distance, -direction.getZ * distance)
  }

  def moveToLeft(distance:Float) {
    trans = trans.translate(left.getX * distance, left.getY * distance, left.getZ * distance)
  }

  def moveToRight(distance:Float) {
    trans = trans.translate(-left.getX * distance, -left.getY * distance, -left.getZ * distance)
  }

  def setScale(scale:Vector3f) {
    this.scale = scale
  }

  def getScale() = scale

  def direction = {
    val dest = new Matrix4f
    rot.transpose(dest)
    val dir = dest.translate(new Vector3f(0, 0, -1), null)
    new Vector3f(dir.m30, dir.m31, dir.m32)
  }
  def top = {
    val dest = new Matrix4f
    rot.transpose(dest)
    val dir = dest.translate(new Vector3f(0, 1, 0), null)
    new Vector3f(dir.m30, dir.m31, dir.m32)
  }
  def left = {
    val dest = new Matrix4f
    rot.transpose(dest)
    val dir = dest.translate(new Vector3f(-1, 0, 0), null)
    new Vector3f(dir.m30, dir.m31, dir.m32)
  }

  def x = trans.getX
  def y = trans.getY
  def z = trans.getZ

  def translation = trans

  def setTranslation(trans: Vector3f) {
    this.trans = trans
  }

  def scaleX = scale.getX
  def scaleY = scale.getY
  def scaleZ = scale.getZ

  def getNegateRotateMatrix = {
    val buffer = BufferUtils.allocateFloatBuffer(16)
    rot.store(buffer)
    buffer.position(0)
    buffer
  }

  def getRotateMatrix = {
    val buffer = BufferUtils.allocateFloatBuffer(16)
    rot.storeTranspose(buffer)
    buffer.position(0)
    buffer
  }

  def quaternionToMatrix(q:Quaternion) = {
    val m = new Matrix4f()
    m.setIdentity()

    val xx = q.x * q.x * 2.0
    val yy = q.y * q.y * 2.0
    val zz = q.z * q.z * 2.0
    val xy = q.x * q.y * 2.0
    val yz = q.y * q.z * 2.0
    val zx = q.z * q.x * 2.0
    val xw = q.x * q.w * 2.0
    val yw = q.y * q.w * 2.0
    val zw = q.z * q.w * 2.0

    m.m00 = (1.0 - yy - zz).toFloat
    m.m01 = (xy + zw).toFloat
    m.m02 = (zx - yw).toFloat

    m.m10 = (xy - zw).toFloat
    m.m11 = (1.0 - zz - xx).toFloat
    m.m12 = (yz + xw).toFloat

    m.m20 = (zx + yw).toFloat
    m.m21 = (yz - xw).toFloat
    m.m22 = (1.0 - xx - yy).toFloat
    m
  }

  private def degreeToRadian(degree:Float):Float = (degree * Math.PI / 180).toFloat

}
