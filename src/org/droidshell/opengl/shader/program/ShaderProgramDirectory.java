package org.droidshell.opengl.shader.program;

import java.util.HashMap;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderProgramDirectory {

	private static final String TAG = ShaderProgramDirectory.class.getName();

	private static HashMap<String, ShaderProgram> shaderPrograms;

	public static void init() {
		shaderPrograms = new HashMap<String, ShaderProgram>();
	}

	public static void put(String programName, ShaderProgram program) {
		shaderPrograms.put(programName, program);
	}

	public static ShaderProgram get(String programName) {
		if (shaderPrograms.containsKey(programName))
			return shaderPrograms.get(programName);

		Log.e(TAG, "Failed to get shader program: " + programName);
		return null;
	}

}
