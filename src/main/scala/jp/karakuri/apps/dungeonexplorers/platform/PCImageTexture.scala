package jp.karakuri.apps.dungeonexplorers.platform

import jp.karakuri.apps.dungeonexplorers.gl.{GL}
import jp.karakuri.apps.dungeonexplorers.texture.ImageTexture

class PCImageTexture(val textureId: Int, val width: Int, val height: Int, val textureWidth: Int, val textureHeight: Int)(implicit val gl: GL) extends ImageTexture {
}
