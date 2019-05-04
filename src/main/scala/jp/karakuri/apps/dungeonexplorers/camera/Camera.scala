package jp.karakuri.apps.dungeonexplorers.camera

import jp.karakuri.apps.dungeonexplorers.interface.Geometry
import jp.karakuri.apps.dungeonexplorers.Modules._
import jp.karakuri.apps.dungeonexplorers.gl.GL
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders
import jp.karakuri.apps.dungeonexplorers.utils.{MatrixUtils, BufferUtils}

class Camera(implicit gl:GL) extends Geometry {
  def use() {
    Shaders.current.setUniform("cameraRot", getNegateRotateMatrix)
    Shaders.current.setUniform("cameraPos", x, y, z)
  }
}
