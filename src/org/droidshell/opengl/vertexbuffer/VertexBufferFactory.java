package org.droidshell.opengl.vertexbuffer;

import java.nio.FloatBuffer;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class VertexBufferFactory {

	@SuppressWarnings("unused")
	private static final String TAG = VertexBufferFactory.class.getName();

	public static void init() {
		VertexBufferDirectory.init();
	}

	public static void build(String vbId, FloatBuffer buffer, int size,
			int glType, boolean isNormalized) throws Exception {
		VertexBufferDirectory.put(vbId, new VertexBuffer(buffer, size, glType,
				isNormalized));
	}

}
