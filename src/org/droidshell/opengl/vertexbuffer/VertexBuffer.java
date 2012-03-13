package org.droidshell.opengl.vertexbuffer;

import java.nio.FloatBuffer;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class VertexBuffer {
	
	@SuppressWarnings("unused")
	private static final String TAG = VertexBuffer.class.getName();
	
	public FloatBuffer buffer;
	public int size;
	public int glType;
	public boolean isNormalized;
	
	public VertexBuffer(FloatBuffer buffer, int size, int glType, boolean isNormalized) {
		this.buffer = buffer;
		this.size = size;
		this.glType = glType;
		this.isNormalized = isNormalized;
	}

}
