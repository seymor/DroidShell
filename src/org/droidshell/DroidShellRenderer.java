package org.droidshell;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.opengl.shader.ShaderAttributeManager;
import org.droidshell.opengl.shader.ShaderManager;
import org.droidshell.opengl.shader.ShaderProgram;
import org.droidshell.opengl.vbo.VertexBufferObject;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class DroidShellRenderer implements Renderer {

	private FloatBuffer triangleVB;
	private FloatBuffer triangleCB;
	private VertexBufferObject vbo;
	private ShaderProgram program;

	public void onDrawFrame(GL10 gl) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		program.use();

		//TODO: bind and prepare VBO buffers
		vbo.addBuffer(0, triangleVB, 3, GLES20.GL_FLOAT, false)
			.addBuffer(1, triangleCB, 3, GLES20.GL_FLOAT, true);
		
		vbo.prepareBuffers(12, 0, 0);

		vbo.draw(GLES20.GL_TRIANGLES, 0, 3);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		initShapes();
		
		ShaderManager.loadShader(R.raw.test_vs, GLES20.GL_VERTEX_SHADER);
		ShaderManager.loadShader(R.raw.test_fs, GLES20.GL_FRAGMENT_SHADER);

		program = new ShaderProgram(
				ShaderManager.getVertexShader(R.raw.test_vs),
				ShaderManager.getFragmentShader(R.raw.test_fs));

		program.create();

		// TODO: get handlers for shader attributes
		ShaderAttributeManager.addAttribute(program.id, "vPosition");
		ShaderAttributeManager.addAttribute(program.id, "vColor");

		vbo = new VertexBufferObject("trájengül");
	}

	private void initShapes() {
		float[] triangleCoords = new float[] {
				// X,Y,Z
				-0.5f, -0.25f, 0, 0.5f, -0.25f, 0, 0.0f, 0.559016994f, 0 };

		ByteBuffer vbb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		triangleVB = vbb.asFloatBuffer();
		triangleVB.put(triangleCoords);
		triangleVB.position(0);

		float[] triangleColors = new float[] { 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f };

		ByteBuffer cbb = ByteBuffer.allocateDirect(triangleColors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		triangleCB = cbb.asFloatBuffer();
		triangleCB.put(triangleColors);
		triangleCB.position(0);
	}
}