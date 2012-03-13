package org.droidshell.opengl.vbo;

public class VertexBufferObjectFactory {
	
	private static final String TAG = VertexBufferObjectFactory.class.getName();
	
	public static void init() {
		VertexBufferObjectDirectory.init();
	}
	
	public static void build(String vboId) throws Exception {		
		VertexBufferObjectDirectory.put(vboId, new VertexBufferObject(vboId));
	}

}
