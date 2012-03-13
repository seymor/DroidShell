package org.droidshell;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.camera.Camera;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector3D;
import org.droidshell.node.NodeList;
import org.droidshell.node.sprite.Sprite;
import org.droidshell.opengl.GLStateManager;
import org.droidshell.opengl.shader.ShaderDirectory;
import org.droidshell.opengl.shader.ShaderFactory;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.shader.program.ShaderProgramDirectory;
import org.droidshell.opengl.shader.program.ShaderProgramFactory;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.texture.TextureFactory;
import org.droidshell.render.RenderContext;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class DroidShellRenderer implements Renderer {

	private NodeList<Sprite> sprites;
	private Sprite sprite;
	private Camera camera;
	private float angle = 0;
	private RenderContext renderContext;

	public void onDrawFrame(GL10 glUnused) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		sprite.modelMatrix = Matrix.identity();

		// Transformation like in OpenGL 1.0
		sprite.translate(-2.5f + angle++/100, -1.0f);
		//sprite.rotate(angle++);
		//sprite.scale(0.8f, 0.8f);

		sprites.renderAll(renderContext);
	}

	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		TextureFactory.buildTextures();

		camera = new Camera(new Vector3D(0, 0, -1), new Vector3D(0, 0, 0), -1, 1, 1,
				100);

		sprites = new NodeList<Sprite>();

		sprite = new Sprite(0.5f, 0.5f, R.drawable.icon);

		ShaderFactory.build(R.raw.test_vs, ShaderFactory.VERTEX_SHADER);
		ShaderFactory.build(R.raw.test_fs, ShaderFactory.FRAGMENT_SHADER);

		ShaderProgramFactory.build("basic",
				ShaderDirectory.getVertexShader(R.raw.test_vs),
				ShaderDirectory.getFragmentShader(R.raw.test_fs));

		ShaderProgram program = ShaderProgramDirectory.get("basic");

		renderContext = new RenderContext(camera, program);
		ShaderProgramInput sI = renderContext.shaderInput;
		sI.bindAttribute(sI.ATTRIBUTE_POS, "vPosition");
		sI.bindAttribute(sI.ATTRIBUTE_COLOR, "vColor");
		sI.bindAttribute(sI.ATTRIBUTE_TEXCOORD, "vTexture");
		
		sI.bindUniform(sI.UNIFORM_MODELMATRIX, "uModelMatrix");
		sI.bindUniform(sI.UNIFORM_MODELVIEWPROJMATRIX, "uModelViewProjMatrix");
		sI.bindUniform(sI.UNIFORM_TEXTURE_SAMPLER, "uTextureSampler");
		
		renderContext.shaderProgram.use();

		sprites.push(sprite);

		GLStateManager.enableAlphaBlending(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}
}