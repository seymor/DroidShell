package org.droidshell.opengl.vertexbuffer;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class VertexBufferFactory {

	@SuppressWarnings("unused")
	private static final String TAG = VertexBufferFactory.class.getName();

	public static void onInit() {
		VertexBufferDirectory.onInit();
	}

	public static void build(String vbId, VertexBuffer buffer) throws Exception {
		VertexBufferDirectory.put(vbId, buffer);
	}

}
