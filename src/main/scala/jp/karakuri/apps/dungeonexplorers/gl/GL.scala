package jp.karakuri.apps.dungeonexplorers.gl

import java.nio.{IntBuffer, ByteBuffer, FloatBuffer}

trait GL {
  def glViewport(x:Int, y:Int, width:Int, height:Int)

  def glEnable(mode: GLConsts)

  def glDisable(mode: GLConsts)

  def glClear(mask: GLConsts)

  def glEnableClientState(cap: GLConsts)

  def glDisableClientState(cap: GLConsts)

  def glGenBuffer(): Int

  def glBindBuffer(target: GLConsts, buffer: Int)

  def glBufferData(target: GLConsts, data: FloatBuffer, usage: GLConsts)

  def glBufferData(target: GLConsts, data: IntBuffer, usage: GLConsts)

  def glDeleteBuffer(id: Int)

  def glGenTexture(): Int

  def glBindTexture(target: GLConsts, buffer: Int)

  def glTexImage2D(target: GLConsts, level: Int, internalFormat: GLConsts, width: Int, height: Int, border: Int, format: GLConsts, pixels: ByteBuffer)

  def glTexImage2D(target: GLConsts, level: Int, internalFormat: GLConsts, width: Int, height: Int, border: Int, format: GLConsts, pixels: FloatBuffer)

  def glTexImage2D(target: GLConsts, level: Int, internalFormat: GLConsts, width: Int, height: Int, border: Int, format: GLConsts, pixels: IntBuffer)

  def glDeleteTexture(id: Int)

  def glVertexPointer(size: Int, type_ : GLConsts, stride: Int, offset: Long)

  def glNormalPointer(type_ : GLConsts, stride: Int, offset: Long)

  def glColorPointer(size: Int, type_ : GLConsts, stride: Int, offset: Long)

  def glTexCoordPointer(size: Int, type_ : GLConsts, stride: Int, offset: Long)

  def glDrawElements(mode: GLConsts, count: Int, offset: Long)

  def glTexParameter(target: GLConsts, pname: GLConsts, param: GLConsts)

  def glBlendFunc(src: GLConsts, dst: GLConsts)

  def glAlphaFunc(func: GLConsts, ref: Float)

  def glDepthMask(flag: Boolean)

  def glGetAttribLocation(programId: Int, name:String):Int

  def glEnableVertexAttribArray(index:Int)

  def glDisableVertexAttribArray(index:Int)

  def glVertexAttribPointer(index:Int, size: Int, type_ : GLConsts, normalized:Boolean, stride: Int, offset: Long)

  def glGetUniformLocation(programId: Int, name:String):Int

  def glUniform(index:Int, matrices:FloatBuffer)

  def glUniform(index:Int, value:Int)

  def glUniform(index:Int, value:Float)

  def glUniform(index:Int, x:Float, y:Float, z:Float)

  def glActiveTexture(index:GLConsts)

  def glCreateVertexShader():Int

  def glCreateFragmentShader():Int

  def glCompileShader(shaderId:Int, shader:String)

  def glCreateProgram(vertShaderId:Int, flagShaderId:Int):Int

  def glLinkProgram(programId:Int)

  def glUseProgram(programId:Int)

  def glGenFramebuffer():Int

  def glBindFramebuffer(framebufferId:Int)

  def glFramebufferTexture2D(target:GLConsts, attachment:GLConsts, textarget:GLConsts, texture:Int, level:Int)

  def glDrawBuffer(buffer:GLConsts)

  def glDrawBuffers(buffers:Array[Int])

  def glCheckFramebufferStatus(target:GLConsts):Int

  def glGenerateMipMap(target:GLConsts)
}