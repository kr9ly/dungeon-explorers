package jp.karakuri.apps.dungeonexplorers.platform

import jp.karakuri.apps.dungeonexplorers.context.Context
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL20._
import jp.karakuri.apps.dungeonexplorers.utils.MatrixUtils
import org.lwjgl.util.vector.Matrix4f
import jp.karakuri.apps.dungeonexplorers.game.shaders.Shaders

class PCContext extends Context {
  override def onInit() {
    glEnable(GL_CULL_FACE)
    glCullFace(GL_BACK)
    glEnable(GL_DEPTH_TEST)
    glDepthFunc(GL_LEQUAL)
  }

  override def preRender() {
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
  }

  override def preObjectRender() {
    Shaders.current.setUniform("useLighting", 1)

    val projection = MatrixUtils.getPerspectiveMatrix(-1, 1, -0.75f, 0.75f, 1, 3000)
    Shaders.current.setUniform("projection", MatrixUtils.matrixToFloatBuffer(projection))
  }

  override def preGuiRender() {
    Shaders.current.setUniform("useLighting", 0)

    glClear(GL_DEPTH_BUFFER_BIT)
    val projection = MatrixUtils.getOrthogonalMatrix(0, 640, -480, 0, -1000, 1000)
    Shaders.current.setUniform("projection", MatrixUtils.matrixToFloatBuffer(projection))

    // カメラをリセット
    val matrix = new Matrix4f()
    matrix.setIdentity()
    Shaders.current.setUniform("cameraRot", MatrixUtils.matrixToFloatBuffer(matrix))
    Shaders.current.setUniform("cameraPos", 0, 0, 0)
  }
}
