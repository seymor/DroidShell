package org.droidshell.opengl.shader.program;

import org.droidshell.exception.ResourceNotFoundException;

import android.util.SparseArray;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderProgramDirectory {

	@SuppressWarnings("unused")
	private static final String TAG = ShaderProgramDirectory.class.getName();

	private static SparseArray<ShaderProgram> shaderPrograms;

	public static void onInit() {
		shaderPrograms = new SparseArray<ShaderProgram>();
	}

	public static void put(String programName, ShaderProgram program) {
		shaderPrograms.put(programName.hashCode(), program);
	}

	public static ShaderProgram get(String programName) {
		ShaderProgram shaderProgram = shaderPrograms.get(programName.hashCode());
		if(shaderProgram == null)
			throw new ResourceNotFoundException("ShaderProgram is not found in directory!");
		return shaderProgram;
	}
	
	public static void remove(String vbId) {
		shaderPrograms.remove(vbId.hashCode());
	}

}
