package org.droidshell.opengl.vbo;

import java.nio.FloatBuffer;

import android.opengl.GLES20;
import android.util.Log;

public class VertexBufferObject {
	
	private static final String TAG = VertexBufferObject.class.getName();

	public static final int TEXTURE_SAMPLER_HANDLER = 0;
	public static final int MODEL_MATRIX_HANDLER = 1;
	
	public final String name;

	public FloatBuffer vertexBuffer;
	public FloatBuffer colorBuffer;
	public FloatBuffer textureBuffer;
	
	public final int[] sizes = new int[3];
	public final int[] glTypes = new int[3];
	public final boolean[] isNormalized = new boolean[3];
	private int[] handlers = new int[3];
	private int[] uniformHandlers = new int[3];

	public VertexBufferObject(String name) {

		this.name = name;
		for(int i = 0; i < 3; i++) {
			sizes[i] = -1;
			glTypes[i] = -1;
			isNormalized[i] = false;
			handlers[i] = -1;
			uniformHandlers[i] = -1;
		}

	}
	
	public VertexBufferObject add(int bufferType, FloatBuffer buffer, int size, int glType, boolean isNormalized) {
		
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
	
	public int getAttributeHandler(int bufferType) throws Exception {
		if (bufferType > 2 || bufferType < 0) {
			throw new Exception("Unknown buffer type! (0,1,2 are valid)");
		}
		return this.handlers[bufferType];
	}
	
	public int getUniformHandler(int uniformType) throws Exception {
		if (uniformType > 2 || uniformType < 0) {
			throw new Exception("Unknown uniform type! (0,1 are valid)");
		}
		return this.uniformHandlers[uniformType];
	}
	
	public void setAttributeHandler(int bufferType, int handler) throws Exception {
		if (bufferType > 2 || bufferType < 0) {
			throw new Exception("Unknown buffer type! (0,1,2 are valid)");
		}
		this.handlers[bufferType] = handler;
	}
	
	public void setUniformHandler(int uniformType, int handler) throws Exception {
		if (uniformType > 2 || uniformType < 0) {
			throw new Exception("Unknown uniform type! (0,1,2 are valid)");
		}
		this.uniformHandlers[uniformType] = handler;
	}
	
	public void prepare() {
		FloatBuffer[] buffers = new FloatBuffer[] { vertexBuffer, colorBuffer, textureBuffer };
		
		for(int i=0; i<3; i++) {
			if(handlers[i] != -1) {
				GLES20.glVertexAttribPointer(handlers[i], sizes[i], glTypes[i], isNormalized[i], 0, buffers[i]);
		        GLES20.glEnableVertexAttribArray(handlers[i]);
			}
		}
		
	}
	
	public void prepareTexture(int textureId) throws Exception {
		if(textureBuffer != null) {
			GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
			GLES20.glUniform1i(uniformHandlers[0], 0);
		}else {
			throw new Exception("Texture not set!");
		}
	}
	
	public void prepareModelMatrix(float[] mx) {
		GLES20.glUniformMatrix4fv(uniformHandlers[1], 1, false, mx, 0);
	}

	public void draw(final int glPrimitive, final int offset, final int count) {
		GLES20.glDrawArrays(glPrimitive, offset, count);
	}

}
