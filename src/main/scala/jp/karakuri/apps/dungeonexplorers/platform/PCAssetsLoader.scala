package jp.karakuri.apps.dungeonexplorers.platform

import java.io.InputStream
import jp.karakuri.apps.dungeonexplorers.loader.AssetsLoader

class PCAssetsLoader extends AssetsLoader {
  override def load(path: String): InputStream = this.getClass.getResourceAsStream("/" + path)
}
