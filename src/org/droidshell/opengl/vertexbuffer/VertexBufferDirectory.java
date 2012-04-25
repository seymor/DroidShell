package org.droidshell.opengl.vertexbuffer;

import org.droidshell.exception.ResourceNotFoundException;

import android.util.SparseArray;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class VertexBufferDirectory {

	@SuppressWarnings("unused")
	private static final String TAG = VertexBufferDirectory.class.getName();

	private static SparseArray<VertexBuffer> buffers;

	public static void onInit() {
		buffers = new SparseArray<VertexBuffer>();
	}

	public static void put(String vbId, VertexBuffer buffer) {
		buffers.put(vbId.hashCode(), buffer);
	}

	public static VertexBuffer get(String name) {
		VertexBuffer buffer = buffers.get(name.hashCode());
		if(buffer == null)
			throw new ResourceNotFoundException("VertexBuffer is not found in directory!");
		return buffer;
	}
	
	public static void remove(String vbId) {
		buffers.remove(vbId.hashCode());
	}

}
