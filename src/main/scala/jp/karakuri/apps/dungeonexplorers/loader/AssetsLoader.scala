package jp.karakuri.apps.dungeonexplorers.loader

import java.io.InputStream

trait AssetsLoader {
  def load(path: String): InputStream
}
