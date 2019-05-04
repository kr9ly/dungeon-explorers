package jp.karakuri.apps.dungeonexplorers.model

import scala.xml.XML
import org.lwjgl.util.vector.{Vector3f, Matrix4f}
import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils
import jp.karakuri.apps.dungeonexplorers.gl.GL
import jp.karakuri.apps.dungeonexplorers.loader.AssetsLoader
import scala.collection.mutable.ArrayBuffer

class ColladaModel(colladaStr:String)(implicit gl:GL) extends Model {
  load(colladaStr)

  def load(str:String) = {
    val xml = XML.loadString(str)

    val mesh = xml \ "library_geometries" \\ "mesh"

    var vertice:Array[Float] = Array()
    var normal:Array[Float] = Array()
    var uv:Array[Float] = Array()

    var vertexId:String = ""
    var normalId:String = ""
    var uvId:String = ""

    var vertexOffset:Int = 0
    var normalOffset:Int = 0
    var uvOffset:Int = 0

    val triangles = mesh \ "triangles"

    vertexId = (mesh \ "vertices" \ "input" \ "@source").text.substring(1)

    for (input <- triangles \ "input") {
      if ((input \ "@semantic").text.equals("VERTEX")) {
        vertexOffset = (input \ "@offset").text.toInt
      } else if ((input \ "@semantic").text.equals("NORMAL")) {
        normalId = (input \ "@source").text.substring(1)
        normalOffset = (input \ "@offset").text.toInt
      } else if ((input \ "@semantic").text.equals("TEXCOORD")) {
        uvId = (input \ "@source").text.substring(1)
        uvOffset = (input \ "@offset").text.toInt
      }
    }

    for (node <- mesh \ "source") {
      val array = (node \ "float_array").text.split(" ").map(_.toFloat)

      if ((node \ "@id").text.equals(vertexId)) {
        vertice = array
      } else if ((node \ "@id").text.equals(normalId)) {
        normal = array
      } else if ((node \ "@id").text.equals(uvId)) {
        uv = array
      }
    }

    val p = (triangles \ "p").text.split(" ").map(_.toInt)
    val vertexBuf = ArrayBuffer[Float]()
    val normalBuf = ArrayBuffer[Float]()
    val uvBuf = ArrayBuffer[Float]()

    val matrix = new Matrix4f()
    matrix.setIdentity()
    matrix.rotate((90 * Math.PI / 180).toFloat, new Vector3f(1, 0, 0))
    matrix.transpose()
    for (i <- Range(0, p.length, 3)) {
      val vertex = new Vector3f(vertice(p(i + vertexOffset) * 3), vertice(p(i + vertexOffset) * 3 + 1), vertice(p(i + vertexOffset) * 3 + 2))
      val vm = matrix.translate(vertex, null)
      vertexBuf += vm.m30
      vertexBuf += vm.m31
      vertexBuf += vm.m32

      radius = Math.max(radius, new Vector3f(vm.m30, vm.m31, vm.m32).length())

      val normalV = new Vector3f(normal(p(i + normalOffset) * 3), normal(p(i + normalOffset) * 3 + 1), normal(p(i + normalOffset) * 3 + 2))
      val mm = matrix.translate(normalV, null)
      normalBuf += mm.m30
      normalBuf += mm.m31
      normalBuf += mm.m32

      uvBuf += uv(p(i + uvOffset) * 2)
      uvBuf += 1f - uv(p(i + uvOffset) * 2 + 1)
    }

    val vertexFB = BufferUtils.allocateFloatBuffer(vertexBuf.toArray)
    val normalFB = BufferUtils.allocateFloatBuffer(normalBuf.toArray)
    val uvFB = BufferUtils.allocateFloatBuffer(uvBuf.toArray)
    val indicesFB = BufferUtils.allocateIntBuffer(Range(0, p.length / 3).toArray)
    val colorFB = BufferUtils.allocateFloatBuffer(vertexFB.limit() / 3 * 4)
    val tangentsFB = BufferUtils.computeTangents(indicesFB, vertexFB, uvFB)
    for (i <- Range(0, colorFB.limit())) {
      colorFB.put(1.0f)
    }

    setVertex(vertexFB)
    setNormal(normalFB)
    setUV(uvFB)
    setIndices(indicesFB)
    setColor(colorFB)
    setTangents(tangentsFB)
  }
}
