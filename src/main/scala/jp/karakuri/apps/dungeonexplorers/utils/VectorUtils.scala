package jp.karakuri.apps.dungeonexplorers.utils

import org.lwjgl.util.vector.{Vector2f, Vector3f}

object VectorUtils {
  def computeTangent(v1:Vector3f, v2:Vector3f, v3:Vector3f, uv1:Vector2f, uv2:Vector2f, uv3:Vector2f) = {
    val v21 = Vector3f.sub(v2, v1, null)
    val v31 = Vector3f.sub(v3, v1, null)
    val uv21 = Vector2f.sub(uv2, uv1, null)
    val uv31 = Vector2f.sub(uv3, uv1, null)

    v21.scale(uv31.getY)
    v31.scale(uv21.getY)

    Vector3f.sub(v21, v31, null).normalise(null)
  }
}
