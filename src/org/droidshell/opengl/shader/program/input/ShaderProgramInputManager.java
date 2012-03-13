package org.droidshell.opengl.shader.program.input;

import java.util.HashMap;

import org.droidshell.exception.ClassNotInitializedException;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderProgramInputManager {

	private static final String TAG = ShaderProgramInputManager.class.getName();
	private static HashMap<String, Integer> attributes;
	private static HashMap<String, Integer> uniforms;

	public static void init() {
		attributes = new HashMap<String, Integer>();
		uniforms = new HashMap<String, Integer>();
	}

	public static void addAttribute(int glProgram, String name) throws Exception {
		if(attributes == null || uniforms == null)
			throw new ClassNotInitializedException("HashMap not initialized!");
		
		if (attributes.containsKey(name))
			return;
		
		int handler = GLES20.glGetAttribLocation(glProgram, name);

		if (handler >= 0)
			attributes.put(name, handler);
		else {
			throw new Exception("Cannot find attribute with name: " + name);
		}
			
	}
	
	public static void addUniform(int glProgram, String name) throws Exception {
		if(attributes == null || uniforms == null)
			throw new ClassNotInitializedException("HashMap not initialized!");
		
		if (uniforms.containsKey(name))
			return;
		
		int handler = GLES20.glGetUniformLocation(glProgram, name);

		if (handler >= 0)
			uniforms.put(name, handler);
		else {
			throw new Exception("Cannot find uniform with name: " + name);
		}
	}

	public static int getAttributeHandler(String name) {
		if(attributes == null)
			throw new ClassNotInitializedException("HashMap not initialized!");
		
		if (attributes.containsKey(name))
			return attributes.get(name);

		Log.e(TAG, "Failed to get shader attribute: " + name);
		return -1;

	}
	
	public static int getUniformHandler(String name) {
		if(uniforms == null)
			throw new ClassNotInitializedException("HashMap not initialized!");
		
		if (uniforms.containsKey(name))
			return uniforms.get(name);

		Log.e(TAG, "Failed to get shader uniform: " + name);
		return -1;
		
	}
}
