package org.droidshell.opengl.shader.program;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderProgramFactory {
	
	@SuppressWarnings("unused")
	private static final String TAG = ShaderProgramFactory.class.getName();
	
	public static void init() {
		ShaderProgramDirectory.init();
	}
	
	public static void build(String name, int vertexShaderId, int fragmentShaderId) {
		ShaderProgram program = new ShaderProgram(vertexShaderId, fragmentShaderId);
		ShaderProgramDirectory.put(name, program.create());
	}

}
