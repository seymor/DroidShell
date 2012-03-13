package org.droidshell;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.camera.Camera;
import org.droidshell.math.Matrix;
import org.droidshell.math.Vector3D;
import org.droidshell.node.NodeList;
import org.droidshell.node.sprite.AnimatedSprite;
import org.droidshell.node.sprite.Sprite;
import org.droidshell.opengl.GLStateManager;
import org.droidshell.opengl.shader.ShaderDirectory;
import org.droidshell.opengl.shader.ShaderFactory;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.shader.program.ShaderProgramDirectory;
import org.droidshell.opengl.shader.program.ShaderProgramFactory;
import org.droidshell.opengl.texture.TextureFactory;
import org.droidshell.opengl.vbo.VertexBufferObject;
import org.droidshell.render.RenderContext;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class DroidShellRenderer implements Renderer {

	private NodeList<AnimatedSprite> sprites;
	private AnimatedSprite sprite;
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

		sprites.updateAll(System.currentTimeMillis());
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

		sprites = new NodeList<AnimatedSprite>();

		sprite = new AnimatedSprite(27, 20, 1.0f, 1.2f, R.drawable.biz, true);

		ShaderFactory.build(R.raw.test_vs, ShaderFactory.VERTEX_SHADER);
		ShaderFactory.build(R.raw.test_fs, ShaderFactory.FRAGMENT_SHADER);

		ShaderProgramFactory.build("basic",
				ShaderDirectory.getVertexShader(R.raw.test_vs),
				ShaderDirectory.getFragmentShader(R.raw.test_fs));

		ShaderProgram program = ShaderProgramDirectory.get("basic");

		renderContext = new RenderContext(camera, program);
		renderContext.bindModelMatrixHandler("uModelMatrix");
		renderContext.bindModelViewProjMatrixHandler("uModelViewProjMatrix");
		renderContext.shaderProgram.use();
		
		sprite.program = program;

		try {
			sprite.bindShaderAttribute(Sprite.POSITION_BUFFER_ID, "vPosition");
			sprite.bindShaderAttribute(Sprite.COLOR_BUFFER_ID, "vColor");
			sprite.bindShaderAttribute(Sprite.TEXTURE_BUFFER_ID, "vTexture");

			sprite.bindShaderUniform(
					VertexBufferObject.TEXTURE_SAMPLER_HANDLER,
					"uTextureSampler");

		} catch (Exception e) {
			Log.e("SAM", e.getMessage());
		}

		

		sprites.push(sprite);

		GLStateManager.enableAlphaBlending(GLES20.GL_SRC_ALPHA,
				GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}
}