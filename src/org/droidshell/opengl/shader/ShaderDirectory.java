package org.droidshell.opengl.shader;

import java.util.HashMap;

import org.droidshell.exception.UnkownShaderTypeException;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderDirectory {

	private static final String TAG = ShaderDirectory.class.getName();

	private static HashMap<Integer, Integer> vertexShaders;
	private static HashMap<Integer, Integer> fragmentShaders;

	public static void init() {
		vertexShaders = new HashMap<Integer, Integer>();
		fragmentShaders = new HashMap<Integer, Integer>();
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
		if (vertexShaders.containsKey(resourceId))
			return vertexShaders.get(resourceId);

		Log.e(TAG, "Failed to get vertex shader: " + resourceId);
		return -1;
	}

	public static int getFragmentShader(final int resourceId) {
		if (fragmentShaders.containsKey(resourceId))
			return fragmentShaders.get(resourceId);

		Log.e(TAG, "Failed to get fragment shader: " + resourceId);
		return -1;
	}
}
