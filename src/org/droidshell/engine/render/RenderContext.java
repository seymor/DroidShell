package org.droidshell.engine.render;

import org.droidshell.engine.render.camera.Camera;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class RenderContext {

	@SuppressWarnings("unused")
	private static final String TAG = RenderContext.class.getName();

	public Camera camera;
	public ShaderProgram shaderProgram;
	public ShaderProgramInput shaderInput;

	/**
	 * Creates a new RenderContext with an existing
	 * {@link org.droidshell.engine.render.camera.Camera Camera} and
	 * {@link org.droidshell.opengl.shader.program.ShaderProgram ShaderProgram}
	 * with
	 * {@link org.droidshell.opengl.shader.program.input.ShaderProgramInput
	 * ShaderProgramInput}
	 * 
	 */

	public RenderContext(Camera camera, ShaderProgram shaderProgram) {
		this.camera = camera;
		this.shaderProgram = shaderProgram;
		shaderInput = new ShaderProgramInput(shaderProgram);
	}

	/**
	 * Creates a new RenderContext with an existing
	 * {@link org.droidshell.engine.render.camera.Camera Camera},
	 * {@link org.droidshell.opengl.shader.program.ShaderProgram ShaderProgram}
	 * and {@link org.droidshell.opengl.shader.program.input.ShaderProgramInput
	 * ShaderProgramInput}
	 * 
	 */

	public RenderContext(Camera camera, ShaderProgram shaderProgram,
			ShaderProgramInput shaderInput) {
		this.camera = camera;
		this.shaderProgram = shaderProgram;
		this.shaderInput = shaderInput;
	}

}
