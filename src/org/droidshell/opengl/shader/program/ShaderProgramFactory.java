package org.droidshell.opengl.shader.program;

public class ShaderProgramFactory {
	
	private static final String TAG = ShaderProgramFactory.class.getName();
	
	public static void init() {
		ShaderProgramDirectory.init();
	}
	
	public static void build(String name, int vertexShaderId, int fragmentShaderId) {
		ShaderProgram program = new ShaderProgram(vertexShaderId, fragmentShaderId);
		ShaderProgramDirectory.put(name, program.create());
	}

}
