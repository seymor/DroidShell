package org.droidshell;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.droidshell.camera.Camera;
import org.droidshell.math.Color;
import org.droidshell.math.Matrix;
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
	private Sprite sprite;
	private Sprite center;
	private Matrix mvp;
	private Matrix mvp2;
	private Camera c;
	private float angle = 0;
	private ShaderProgram program;

	public void onDrawFrame(GL10 glUnused) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

		sprite.modelMatrix = Matrix.identity();
		center.modelMatrix = Matrix.identity();
		
		//Transformation
		sprite.translate(0.5f, 0.5f);
		sprite.rotate(angle++);
		sprite.scale(0.8f, 0.8f);
		
		center.translate(-0.7f, -0.2f);
		center.rotate(-angle/2);
		
		mvp = c.projMatrix.multiplyN(c.viewMatrix).multiplyN(sprite.modelMatrix);
		mvp2 = c.projMatrix.multiplyN(c.viewMatrix).multiplyN(center.modelMatrix);
		sprite.modelMatrix = mvp;
		center.modelMatrix = mvp2;
		sprites.drawAll();
	}

	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		GLES20.glViewport(0, 0, width, height);		
	}

	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);

		TextureManager.loadImages();
		
		c = new Camera();
		
		sprites = new NodeList<Sprite>();
		
		sprite = new Sprite(new Vector2D(0,0), 1.0f, 1.0f, Color.WHITE, 
				TextureManager.getTexture(R.drawable.splash));
		
		center = new Sprite(new Vector2D(0,0), 0.2f, 0.2f, Color.RED,
				TextureManager.getTexture(R.drawable.icon));
		
		ShaderManager.build(R.raw.test_vs, ShaderManager.VERTEX_SHADER);
		ShaderManager.build(R.raw.test_fs, ShaderManager.FRAGMENT_SHADER);

		program = new ShaderProgram(
				ShaderManager.getVertexShader(R.raw.test_vs),
				ShaderManager.getFragmentShader(R.raw.test_fs));
		
		program.create();
		
		sprite.addShaderProgram(program);
		center.addShaderProgram(program);
		
		c.projMatrix = Matrix.projMatrix(c.ratio, -c.ratio, -1, 1, 1, 100);
		c.viewMatrix = Matrix.lookAtMatrix(0, 0, -1, 0, 0, 0, 0, 1, 0);
		
		mvp = c.projMatrix.multiplyN(c.viewMatrix).multiplyN(sprite.modelMatrix);
		mvp2 = c.projMatrix.multiplyN(c.viewMatrix).multiplyN(center.modelMatrix);
		
		sprite.modelMatrix = mvp;
		center.modelMatrix = mvp2;
		
		try {
			sprite.bindShaderAttribute(Sprite.POSITION_BUFFER_ID, "vPosition");
			sprite.bindShaderAttribute(Sprite.COLOR_BUFFER_ID, "vColor");
			sprite.bindShaderAttribute(Sprite.TEXTURE_BUFFER_ID, "vTexture");
			
			sprite.bindShaderUniform(VertexBufferObject.TEXTURE_SAMPLER_HANDLER, "uTextureSampler");
			sprite.bindShaderUniform(VertexBufferObject.MODEL_MATRIX_HANDLER, "uModelMatrix");
			
			center.bindShaderAttribute(Sprite.POSITION_BUFFER_ID, "vPosition");
			center.bindShaderAttribute(Sprite.COLOR_BUFFER_ID, "vColor");
			center.bindShaderAttribute(Sprite.TEXTURE_BUFFER_ID, "vTexture");
			
			center.bindShaderUniform(VertexBufferObject.TEXTURE_SAMPLER_HANDLER, "uTextureSampler");
			center.bindShaderUniform(VertexBufferObject.MODEL_MATRIX_HANDLER, "uModelMatrix");
		} catch (Exception e) {
			Log.e("SAM",e.getMessage());
		}
		
		program.use();
		
		sprites.push(sprite).push(center);
		
		GLStateManager.enableAlphaBlending(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}
}