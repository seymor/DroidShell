package org.droidshell.opengl.vbo;

import java.util.HashMap;

import android.util.Log;

public class VertexBufferObjectDirectory {
	
	private static final String TAG = VertexBufferObjectDirectory.class.getName();

	private static HashMap<String, VertexBufferObject> vbos;

	public static void init() {
		vbos = new HashMap<String, VertexBufferObject>();
	}

	public static void put(String vboId, VertexBufferObject vbo) {
		vbos.put(vboId, vbo);
	}
	
	public static VertexBufferObject get(String name) {
		if(vbos.containsKey(name))
			return vbos.get(name);
		
		Log.e(TAG, "Failed to get VBO: " + name);
		return null;
	}

}
