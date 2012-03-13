package org.droidshell.render;

import org.droidshell.camera.Camera;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.shader.program.input.ShaderProgramInputManager;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class RenderContext {
	
	private static final String TAG = RenderContext.class.getName();

	public Camera camera;
	public ShaderProgram shaderProgram;
	public int modelMatrixHandler;
	public int modelViewProjMatrixHandler;

	public RenderContext(Camera camera, ShaderProgram shaderProgram) {
		this.camera = camera;
		this.shaderProgram = shaderProgram;
	}
	
	public void bindModelMatrixHandler(String uniformName) {
		try {
			ShaderProgramInputManager.addUniform(shaderProgram.id, uniformName);
			modelMatrixHandler = ShaderProgramInputManager.getUniformHandler(uniformName);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	public void bindModelViewProjMatrixHandler(String uniformName) {
		try {
			ShaderProgramInputManager.addUniform(shaderProgram.id, uniformName);
			modelViewProjMatrixHandler = ShaderProgramInputManager.getUniformHandler(uniformName);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

}
