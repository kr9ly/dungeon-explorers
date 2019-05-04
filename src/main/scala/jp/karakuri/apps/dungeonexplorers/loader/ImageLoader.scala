package jp.karakuri.apps.dungeonexplorers.loader

import jp.karakuri.apps.dungeonexplorers.texture.Texture

trait ImageLoader {
  def load(path: String): Texture
}
