package org.droidshell.opengl.vbo;

import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class VertexBufferObject {
	
	public final String name;
	
	public int handler;
	
	public final int size;
	public final int glType;
	public final boolean isNormalized;
	
	public FloatBuffer buffer;
	
	public VertexBufferObject(final String name, final int size, final int glType, final boolean isNormalized, FloatBuffer buffer) {
		
		this.name = name;
		this.size = size;
		this.glType = glType;
		this.isNormalized = isNormalized;
		
		this.buffer = buffer;
		
	}
	
	public void getHandler(int glProgram) {
		this.handler = GLES20.glGetAttribLocation(glProgram, this.name);
	}
	
	public void prepareData(final int stride) {
		GLES20.glVertexAttribPointer(handler, size, glType, isNormalized, stride, buffer);
		GLES20.glEnableVertexAttribArray(handler);
	}
	
	public void draw(final int glPrimitive, final int offset, final int count) {
		GLES20.glDrawArrays(glPrimitive, offset, count);
	}
	
	
}
