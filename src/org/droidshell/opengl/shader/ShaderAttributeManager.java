package org.droidshell.opengl.shader;

import java.util.HashMap;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderAttributeManager {

	private static final String TAG = ShaderAttributeManager.class.getName();
	private static HashMap<String, Integer> attributes;

	public static void init() {

		attributes = new HashMap<String, Integer>();

	}

	public static void addAttribute(int glProgram, String name) {

		int handler = GLES20.glGetAttribLocation(glProgram, name);

		if (handler >= 0)
			attributes.put(name, handler);
		else
			Log.e(TAG, "Cannot find attribute with name: " + name);

	}

	public static int getHandler(String name) {

		if (attributes.containsKey(name))
			return attributes.get(name);

		Log.e(TAG, "Failed to get shader attribute: " + name);
		return -1;

	}
}
