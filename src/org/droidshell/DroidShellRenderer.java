package org.droidshell;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.math.Color;
import org.droidshell.math.Vector2D;
import org.droidshell.node.NodeList;
import org.droidshell.node.Sprite;
import org.droidshell.opengl.GLStateManager;
import org.droidshell.opengl.shader.ShaderManager;
import org.droidshell.opengl.shader.ShaderProgram;
import org.droidshell.opengl.texture.TextureManager;
import org.droidshell.opengl.vbo.VertexBufferObject;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class DroidShellRenderer implements Renderer {

	private NodeList<Sprite> sprites;
	private ShaderProgram program;

	public void onDrawFrame(GL10 glUnused) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		sprites.drawAll();
	}

	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		TextureManager.loadImages();
		
		sprites = new NodeList<Sprite>();
		
		Sprite sprite = new Sprite(new Vector2D(0,0), 0.5f, 0.5f, Color.WHITE, 
				TextureManager.getTexture(R.drawable.splash));
		
		ShaderManager.build(R.raw.test_vs, ShaderManager.VERTEX_SHADER);
		ShaderManager.build(R.raw.test_fs, ShaderManager.FRAGMENT_SHADER);

		program = new ShaderProgram(
				ShaderManager.getVertexShader(R.raw.test_vs),
				ShaderManager.getFragmentShader(R.raw.test_fs));
		
		program.create();
		
		sprite.addShaderProgram(program);
		
		try {
			sprite.bindShaderAttribute(Sprite.POSITION_BUFFER_ID, "vPosition");
			sprite.bindShaderAttribute(Sprite.COLOR_BUFFER_ID, "vColor");
			sprite.bindShaderAttribute(Sprite.TEXTURE_BUFFER_ID, "vTexture");
			
			sprite.bindShaderUniform(VertexBufferObject.TEXTURE_SAMPLER_HANDLER, "uTextureSampler");
			sprite.bindShaderUniform(VertexBufferObject.MODEL_MATRIX_HANDLER, "uModelMatrix");
		} catch (Exception e) {
			Log.e("SAM",e.getMessage());
		}
		
		program.use();
		
		sprites.push(sprite);
		
		GLStateManager.enableAlphaBlending(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}
}