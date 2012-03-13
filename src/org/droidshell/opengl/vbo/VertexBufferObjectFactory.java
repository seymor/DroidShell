package org.droidshell.opengl.vbo;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class VertexBufferObjectFactory {
	
	private static final String TAG = VertexBufferObjectFactory.class.getName();
	
	public static void init() {
		VertexBufferObjectDirectory.init();
	}
	
	public static void build(String vboId) throws Exception {		
		VertexBufferObjectDirectory.put(vboId, new VertexBufferObject(vboId));
	}

}
