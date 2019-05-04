package jp.karakuri.apps.dungeonexplorers.platform

import jp.karakuri.apps.dungeonexplorers.gl.{GLConsts, GL}
import org.lwjgl.opengl._
import java.nio.{IntBuffer, ByteBuffer, FloatBuffer}
import jp.karakuri.apps.dungeonexplorers.utils.BufferUtils

class PCGL extends GL {
  override def glViewport(x:Int, y:Int, width:Int, height:Int) {
    GL11.glViewport(x, y, width, height)
  }

  override def glGenBuffer() = GL15.glGenBuffers()

  override def glBindBuffer(target: GLConsts, buffer: Int) {
    GL15.glBindBuffer(target, buffer)
  }

  override def glBufferData(target: GLConsts, data: FloatBuffer, usage: GLConsts) {
    GL15.glBufferData(target, data, usage)
  }

  override def glBufferData(target: GLConsts, data: IntBuffer, usage: GLConsts) {
    GL15.glBufferData(target, data, usage)
  }

  override def glDeleteBuffer(id: Int) {
    GL15.glDeleteBuffers(id)
  }

  override def glGenTexture() = GL11.glGenTextures()

  override def glBindTexture(target: GLConsts, buffer: Int) {
    GL11.glBindTexture(target, buffer)
  }

  override def glTexImage2D(target: GLConsts, level: Int, internalFormat: GLConsts, width: Int, height: Int, border: Int, format: GLConsts, pixels: ByteBuffer) {
    GL11.glTexImage2D(target, level, internalFormat, width, height, border, format, GLConsts.GL_UNSIGNED_BYTE, pixels)
  }

  override def glTexImage2D(target: GLConsts, level: Int, internalFormat: GLConsts, width: Int, height: Int, border: Int, format: GLConsts, pixels: FloatBuffer) {
    GL11.glTexImage2D(target, level, internalFormat, width, height, border, format, GLConsts.GL_FLOAT, pixels)
  }

  override def glTexImage2D(target: GLConsts, level: Int, internalFormat: GLConsts, width: Int, height: Int, border: Int, format: GLConsts, pixels: IntBuffer) {
    GL11.glTexImage2D(target, level, internalFormat, width, height, border, format, GLConsts.GL_UNSIGNED_INT, pixels)
  }

  override def glEnable(mode: GLConsts) {
    GL11.glEnable(mode)
  }

  override def glDisable(mode: GLConsts) {
    GL11.glDisable(mode)
  }

  override def glClear(mask: GLConsts) {
    GL11.glClear(mask)
  }

  override def glDeleteTexture(id: Int) {
    GL11.glDeleteTextures(id)
  }

  override def glEnableClientState(cap: GLConsts) {
    GL11.glEnableClientState(cap)
  }

  override def glDisableClientState(cap: GLConsts) {
    GL11.glDisableClientState(cap)
  }

  override def glVertexPointer(size: Int, type_ : GLConsts, stride: Int, offset: Long) {
    GL11.glVertexPointer(size, type_, stride, offset)
  }

  override def glNormalPointer(type_ : GLConsts, stride: Int, offset: Long) {
    GL11.glNormalPointer(type_, stride, offset)
  }

  override def glColorPointer(size: Int, type_ : GLConsts, stride: Int, offset: Long) {
    GL11.glColorPointer(size, type_, stride, offset)
  }

  override def glTexCoordPointer(size: Int, type_ : GLConsts, stride: Int, offset: Long) {
    GL11.glTexCoordPointer(size, type_, stride, offset)
  }

  override def glDrawElements(mode: GLConsts, count: Int, offset: Long) {
    GL11.glDrawElements(mode, count, GLConsts.GL_UNSIGNED_INT, offset)
  }

  override def glTexParameter(target: GLConsts, pname: GLConsts, param: GLConsts) {
    GL11.glTexParameteri(target, pname, param)
  }

  override def glBlendFunc(src: GLConsts, dst: GLConsts) {
    GL11.glBlendFunc(src, dst)
  }

  override def glAlphaFunc(func: GLConsts, ref: Float) {
    GL11.glAlphaFunc(func, ref)
  }

  override def glDepthMask(flag: Boolean) {
    GL11.glDepthMask(flag)
  }

  override def glGetAttribLocation(program: Int, name:String) = GL20.glGetAttribLocation(program, name)

  override def glEnableVertexAttribArray(index:Int) {
    GL20.glEnableVertexAttribArray(index)
  }

  override def glDisableVertexAttribArray(index:Int) {
    GL20.glDisableVertexAttribArray(index)
  }

  override def glVertexAttribPointer(index:Int, size: Int, type_ : GLConsts, normalized:Boolean, stride: Int, offset: Long) {
    GL20.glVertexAttribPointer(index, size, type_, normalized, stride, offset)
  }

  override def glGetUniformLocation(programId: Int, name:String):Int = GL20.glGetUniformLocation(programId, name)

  override def glUniform(index:Int, matrices:FloatBuffer) {
    GL20.glUniformMatrix4(index, false, matrices)
  }

  override def glUniform(index:Int, value:Int) {
    GL20.glUniform1i(index, value)
  }

  override def glUniform(index:Int, value:Float) {
    GL20.glUniform1f(index, value)
  }

  override def glUniform(index:Int, x:Float, y:Float, z:Float) {
    GL20.glUniform3f(index, x, y, z)
  }

  override def glActiveTexture(index:GLConsts) {
    GL13.glActiveTexture(index)
  }

  override def glCreateVertexShader():Int = GL20.glCreateShader(GLConsts.GL_VERTEX_SHADER)

  override def glCreateFragmentShader():Int = GL20.glCreateShader(GLConsts.GL_FRAGMENT_SHADER)

  override def glCompileShader(shaderId:Int, shader:String) {
    GL20.glShaderSource(shaderId, shader)
    GL20.glCompileShader(shaderId)

    val length = GL20.glGetShader(shaderId, GLConsts.GL_INFO_LOG_LENGTH)
    if (length > 1) {
      val log = GL20.glGetShaderInfoLog(shaderId, length)
      println(log)
    }

    val compiled = GL20.glGetShader(shaderId, GLConsts.GL_COMPILE_STATUS)
    if (compiled == GLConsts.GL_FALSE.value) {
      throw new RuntimeException("Compile error in shader")
    }
  }

  override def glCreateProgram(vertShaderId:Int, flagShaderId:Int):Int = {
    val programId = GL20.glCreateProgram()
    GL20.glAttachShader(programId, vertShaderId)
    GL20.glAttachShader(programId, flagShaderId)

    programId
  }

  override def glLinkProgram(programId:Int) {
    GL20.glLinkProgram(programId)

    val length = GL20.glGetProgram(programId, GLConsts.GL_INFO_LOG_LENGTH)
    if (length > 1) {
      val log = GL20.glGetProgramInfoLog(programId, length)
      println(log)
    }

    val linked = GL20.glGetProgram(programId, GLConsts.GL_LINK_STATUS)
    if (linked == GLConsts.GL_FALSE.value) {
      throw new RuntimeException("Link error")
    }
  }

  override def glUseProgram(programId:Int) {
    GL20.glUseProgram(programId)
  }

  override def glGenFramebuffer():Int = GL30.glGenFramebuffers()

  override def glBindFramebuffer(framebufferId:Int) {
    GL30.glBindFramebuffer(GLConsts.GL_FRAMEBUFFER, framebufferId)
  }

  override def glFramebufferTexture2D(target:GLConsts, attachment:GLConsts, textarget:GLConsts, texture:Int, level:Int) {
    GL30.glFramebufferTexture2D(target, attachment, textarget, texture, level)
  }

  override def glDrawBuffer(buffer:GLConsts) {
    GL20.glDrawBuffers(buffer)
  }

  override def glDrawBuffers(buffers:Array[Int]) {
    GL20.glDrawBuffers(BufferUtils.allocateIntBuffer(buffers))
  }

  override def glCheckFramebufferStatus(target:GLConsts) = GL30.glCheckFramebufferStatus(target)

  override def glGenerateMipMap(target:GLConsts) {
    GL30.glGenerateMipmap(target)
  }
}
