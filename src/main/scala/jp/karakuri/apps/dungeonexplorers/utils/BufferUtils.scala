package jp.karakuri.apps.dungeonexplorers.utils

import java.nio.{FloatBuffer, IntBuffer, ByteOrder, ByteBuffer}
import org.lwjgl.util.vector.{Vector2f, Vector3f}
import jp.karakuri.apps.dungeonexplorers.gl.GLConsts

object BufferUtils {
  def allocateFloatBuffer(arr: Array[Float]) = {
    val buf = ByteBuffer.allocateDirect(arr.length * 4)
    buf.order(ByteOrder.nativeOrder)
    val fb = buf.asFloatBuffer()
    fb.put(arr)
    fb.position(0)
    fb
  }

  def allocateFloatBuffer(size: Int) = {
    val buf = ByteBuffer.allocateDirect(size * 4)
    buf.order(ByteOrder.nativeOrder)
    val fb = buf.asFloatBuffer()
    fb.position(0)
    fb
  }

  def allocateIntBuffer(arr: Array[Int]) = {
    val buf = ByteBuffer.allocateDirect(arr.length * 4)
    buf.order(ByteOrder.nativeOrder)
    val ib = buf.asIntBuffer()
    ib.put(arr)
    ib.position(0)
    ib
  }

  def allocateByteBuffer(arr: Array[Byte]) = {
    val buf = ByteBuffer.allocateDirect(arr.length * 4)
    buf.order(ByteOrder.nativeOrder)
    buf.put(arr)
    buf.position(0)
    buf
  }

  def computeTangents(indices:IntBuffer, vertex: FloatBuffer, uv: FloatBuffer) = {
    vertex.position(0)
    uv.position(0)

    val tangents = BufferUtils.allocateFloatBuffer(vertex.limit())
    var index = 0

    while(index < indices.limit()) {
      val i1 = indices.get(index)
      val i2 = indices.get(index+1)
      val i3 = indices.get(index+2)

      index += 3

      val v1 = new Vector3f(vertex.get(i1*3), vertex.get(i1*3+1), vertex.get(i1*3+2))
      val v2 = new Vector3f(vertex.get(i2*3), vertex.get(i2*3+1), vertex.get(i2*3+2))
      val v3 = new Vector3f(vertex.get(i3*3), vertex.get(i3*3+1), vertex.get(i3*3+2))

      val uv1 = new Vector2f(uv.get(i1*2), uv.get(i1*2+1))
      val uv2 = new Vector2f(uv.get(i2*2), uv.get(i2*2+1))
      val uv3 = new Vector2f(uv.get(i3*2), uv.get(i3*2+1))

      val tangent = VectorUtils.computeTangent(v1, v2, v3, uv1, uv2, uv3)

      tangents.put(i1*3, tangent.getX).put(i1*3+1, tangent.getY).put(i1*3+2, tangent.getZ)
      tangents.put(i2*3, tangent.getX).put(i2*3+1, tangent.getY).put(i2*3+2, tangent.getZ)
      tangents.put(i3*3, tangent.getX).put(i3*3+1, tangent.getY).put(i3*3+2, tangent.getZ)
    }

    tangents
  }
}
