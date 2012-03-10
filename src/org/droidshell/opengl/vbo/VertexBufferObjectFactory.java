package org.droidshell.opengl.vbo;

import java.util.HashMap;

import android.util.Log;

public class VertexBufferObjectFactory {
	
	private static final String TAG = VertexBufferObjectFactory.class.getName();
	private static HashMap<String, VertexBufferObject> vbos;
	
	public static void init() {
		vbos = new HashMap<String, VertexBufferObject>();
	}
	
	public static void build(String name) throws Exception {
		if(vbos.containsKey(name)) {
			throw new Exception("VBO already exists!");
		} else
			vbos.put(name, new VertexBufferObject(name));
	}
	
	public static VertexBufferObject getVBO(String name) {
		if(vbos.containsKey(name))
			return vbos.get(name);
		
		Log.e(TAG, "Failed to get VBO: " + name);
		return null;
	}

}
