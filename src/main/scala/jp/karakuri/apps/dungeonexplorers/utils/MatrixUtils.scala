package jp.karakuri.apps.dungeonexplorers.utils

import org.lwjgl.util.vector.{Vector3f, Matrix4f}
import jp.karakuri.apps.dungeonexplorers.camera.Camera

object MatrixUtils {
  def getOrthogonalMatrix(left:Float, right:Float, bottom:Float, top:Float, near:Float, far:Float) = {
    val matrix = new Matrix4f()
    matrix.setIdentity()

    matrix.m00 = 2 / (right - left)//0
    matrix.m11 = 2 / (top - bottom)//5
    matrix.m22 = -2 / (far - near)//10
    matrix.m30 = - (right + left) / (right - left)//12
    matrix.m31 = - (top + bottom) / (top - bottom)//13
    matrix.m32 = - (far+near) / (far - near)//14
    matrix.m33 = 1

    matrix
  }

  def getPerspectiveMatrix(left:Float, right:Float, bottom:Float, top:Float, near:Float, far:Float) = {
    val matrix = new Matrix4f()
    matrix.setIdentity()

    matrix.m00 = 2 * near / (right - left)//0
    matrix.m11 = 2 * near / (top - bottom)//5
    matrix.m22 = -(far + near) / (far - near)//10
    matrix.m23 = -1//11
    matrix.m32 = -2 * far * near / (far - near)//14
    matrix.m20 = (right + left) / (right - left)//8
    matrix.m21 = (top + bottom)/(top-bottom)//9
    matrix.m33 = 0

    matrix
  }

  def matrixToFloatBuffer(matrix:Matrix4f) = {
    val buffer = BufferUtils.allocateFloatBuffer(16)
    matrix.store(buffer)
    buffer.position(0)
    buffer
  }
}
