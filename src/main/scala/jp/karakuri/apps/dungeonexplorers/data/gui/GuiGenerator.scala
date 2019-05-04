package jp.karakuri.apps.dungeonexplorers.data.gui

import jp.karakuri.apps.dungeonexplorers.gl.GL
import scala.xml.{NodeSeq, Elem, Node, XML}
import jp.karakuri.apps.dungeonexplorers.shape.{TextBox, Window}
import jp.karakuri.apps.dungeonexplorers.texture.{FontTexture, Texture}

class GuiGenerator(val guiStr:String)(implicit gl:GL) {
  val root = XML.loadString(guiStr)

  private var fontTexture:FontTexture = null
  private var windowTexture:Texture = null

  def setFontTexture(fontTexture:FontTexture) {
    this.fontTexture = fontTexture
  }

  def setWindowTexture(windowTexture:Texture) {
    this.windowTexture = windowTexture
  }

  def createShapes(node: NodeSeq = root) = {
    node(0).child.flatMap(n => n match {
      case Elem(_, "window", _, _, d) => {
        for (width <- n \ "@width";
              height <- n \ "@height";
              left <- n \ "@left";
              top <- n \ "@top") yield {
          val node = new Window(windowTexture, width.text.toInt, height.text.toInt)
          node.moveToX(left.text.toInt)
          node.moveToY(-top.text.toInt)
          node
        }
      }

      case Elem(_, "text", _, _, d) => {
        for (size <- n \ "@size";
             color <- n \ "@color";
             text <- n \ "@text";
             left <- n \ "@left";
             top <- n \ "@top") yield {
          val node = new TextBox(fontTexture, text.text.replace("\\n", "\n"), size.text.toInt, (1, 1, 1))
          node.moveToX(left.text.toInt)
          node.moveToY(-top.text.toInt)
          node
        }
      }

      case _ => Seq()
    })
  }
}
