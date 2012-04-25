package org.droidshell.opengl.shader;

import org.droidshell.exception.ResourceNotFoundException;
import org.droidshell.exception.UnkownShaderTypeException;

import android.util.SparseIntArray;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderDirectory {

	@SuppressWarnings("unused")
	private static final String TAG = ShaderDirectory.class.getName();

	private static SparseIntArray vertexShaders;
	private static SparseIntArray fragmentShaders;

	public static void onInit() {
		vertexShaders = new SparseIntArray();
		fragmentShaders = new SparseIntArray();
	}

	public static void put(int glShaderType, int resourceId, int shaderId)
			throws Exception {

		if (glShaderType == ShaderFactory.VERTEX_SHADER)
			vertexShaders.put(resourceId, shaderId);
		else if (glShaderType == ShaderFactory.FRAGMENT_SHADER)
			fragmentShaders.put(resourceId, shaderId);
		else
			throw new UnkownShaderTypeException("Unkown shader type!");
	}

	public static int getVertexShader(final int resourceId) {
		int vertexShader = vertexShaders.get(resourceId);
		if(vertexShader == 0)
			throw new ResourceNotFoundException("VertexShader is not found in directory!");
		return vertexShader;
	}

	public static int getFragmentShader(final int resourceId) {
		int fragmentShader = fragmentShaders.get(resourceId);
		if(fragmentShader == 0)
			throw new ResourceNotFoundException("FragmentShader is not found in directory!");
		return fragmentShader;
	}
	
	public static void removeVertexShader(final int resourceId) {
		vertexShaders.removeAt(vertexShaders.indexOfKey(resourceId));
	}
	
	public static void removeFragmentShader(final int resourceId) {
		fragmentShaders.removeAt(fragmentShaders.indexOfKey(resourceId));
	}
}
