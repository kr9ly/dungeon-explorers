package jp.karakuri.apps.dungeonexplorers.loader

import jp.karakuri.apps.dungeonexplorers.texture.Texture

trait FontLoader {
  def load(path: String): Texture
}
