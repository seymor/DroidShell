package org.droidshell.opengl.vbo;

import java.nio.FloatBuffer;

import android.opengl.GLES20;
import android.util.Log;

public class VertexBufferObject {
	
	private static final String TAG = VertexBufferObject.class.getName();

	public final String name;

	public FloatBuffer vertexBuffer;
	public FloatBuffer colorBuffer;
	public FloatBuffer textureBuffer;
	
	public final int[] sizes = new int[3];
	public final int[] glTypes = new int[3];
	public final boolean[] isNormalized = new boolean[3];
	private int[] handlers = new int[3];

	public VertexBufferObject(String name) {

		this.name = name;
		for(int i = 0; i < 3; i++) {
			sizes[i] = -1;
			glTypes[i] = -1;
			isNormalized[i] = false;
			handlers[i] = -1;
		}

	}
	
	public VertexBufferObject addBuffer(int bufferType, FloatBuffer buffer, int size, int glType, boolean isNormalized) {
		
		switch(bufferType) {
		
			case 0: 
				vertexBuffer = buffer;
				sizes[0] = size;
				glTypes[0] = glType;
				this.isNormalized[0] = isNormalized;
				break;
			
			case 1: 
				colorBuffer = buffer;
				sizes[1] = size;
				glTypes[1] = glType;
				this.isNormalized[1] = isNormalized;
				break;
			
			case 2: 
				textureBuffer = buffer;
				sizes[2] = size;
				glTypes[2] = glType;
				this.isNormalized[2] = isNormalized;
				break;
				
			default:
				Log.e(TAG, "Unknown buffer type!");
				break;
			
		}
		
		return this;
	}
	
	public int getHandler(int bufferType) throws Exception {
		if (bufferType > 2 || bufferType < 0) {
			throw new Exception("Unknown buffer type! (0,1,2 are valid)");
		}
		return this.handlers[bufferType];
	}
	
	public void setHandler(int bufferType, int handler) throws Exception {
		if (bufferType > 2 || bufferType < 0) {
			throw new Exception("Unknown buffer type! (0,1,2 are valid)");
		}
		this.handlers[bufferType] = handler;
	}
	
	public void prepareBuffers(int vertexStride, int colorStride, int textureStride) {
		int[] strides = new int[] { vertexStride, colorStride, textureStride };
		FloatBuffer[] buffers = new FloatBuffer[] { vertexBuffer, colorBuffer, textureBuffer };
		
		for(int i=0; i<3; i++) {
			if(handlers[i] != -1) {
				GLES20.glVertexAttribPointer(handlers[i], sizes[i], glTypes[i], isNormalized[i], strides[i], buffers[i]);
		        GLES20.glEnableVertexAttribArray(handlers[i]);
			}
			
		}
	}

	public void draw(final int glPrimitive, final int offset, final int count) {
		GLES20.glDrawArrays(glPrimitive, offset, count);
	}

}
