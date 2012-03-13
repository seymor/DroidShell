package org.droidshell.opengl.vertexbuffer;

import java.util.HashMap;

import android.util.Log;

public class VertexBufferDirectory {

	private static final String TAG = VertexBufferDirectory.class.getName();
	
	private static HashMap<String, VertexBuffer> buffers;
	
	public static void init() {
		buffers = new HashMap<String, VertexBuffer>();
	}
	
	public static void put(String vbId, VertexBuffer buffer) {
		buffers.put(vbId, buffer);
	}
	
	public static VertexBuffer get(String name) {
		if(buffers.containsKey(name))
			return buffers.get(name);
		
		Log.e(TAG, "Failed to get vertex buffer: " + name);
		return null;
	}
	
}