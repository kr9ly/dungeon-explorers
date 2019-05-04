package jp.karakuri.apps.dungeonexplorers

object Predef {
  def using[A <% java.io.Closeable](s: A)(f: A => Any) {
    try f(s) finally s.close()
  }

  def isHalfLetter(c: Char): Boolean = (c <= '\u007e') || (c == '\u00a5') || (c == '\u203e') || (c >= '\uff61' && c <= '\uff9f')
}
