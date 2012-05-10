package org.droidshell.node.sprite;

import java.nio.FloatBuffer;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.rectangle.Rectangle;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.texture.Texture;
import org.droidshell.opengl.texture.TextureDirectory;
import org.droidshell.opengl.vertexbuffer.VertexBuffer;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;

import android.opengl.GLES20;
import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Sprite extends Rectangle {

	private static final String TAG = Sprite.class.getName();

	public VertexBuffer texcoordBuffer;

	public Texture texture;

	public Sprite(float width, float height, int textureId) {
		super(width, height);

		createTextureBuffer();

		texture = TextureDirectory.get(textureId);
	}

	public Sprite(float width, float height, Color color, int textureId) {
		super(width, height, color);

		createTextureBuffer();

		texture = TextureDirectory.get(textureId);
	}

	public Sprite(Vector2D center, float width, float height, int textureId) {
		super(center, width, height);

		createTextureBuffer();

		texture = TextureDirectory.get(textureId);
	}

	public Sprite(Vector2D center, float width, float height, Color color,
			int textureId) {
		super(center, width, height, color);

		createTextureBuffer();

		texture = TextureDirectory.get(textureId);
	}

	private float[] createTextureArray() {
		float[] textureArray = new float[] { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f };

		return textureArray;
	}

	private void createTextureBuffer() {
		String vbId = "TEXTURE" + ":" + "NORMAL";

		FloatBuffer buffer = this.createBuffer(this.createTextureArray());
		texcoordBuffer = new VertexBuffer(buffer, 2, GLES20.GL_FLOAT, true);

		try {
			VertexBufferFactory.build(vbId, texcoordBuffer);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	@Override
	public void onUpdate(long gameTime) {
		super.onUpdate(gameTime);
		
	}

	@Override
	public void onRender(RenderContext renderContext) {
		ShaderProgramInput sI = renderContext.shaderInput;

		sI.prepareVertex(sI.ATTRIBUTE_POS, positionBuffer);
		sI.prepareVertex(sI.ATTRIBUTE_COLOR, colorBuffer);
		sI.prepareVertex(sI.ATTRIBUTE_TEXCOORD, texcoordBuffer);

		sI.prepareMatrix(sI.UNIFORM_MODELMATRIX, modelMatrix.toFloatArray());
		sI.prepareMatrix(sI.UNIFORM_VIEWPROJMATRIX,
				renderContext.camera.viewProjMatrix.toFloatArray());
		sI.prepareTexture(sI.UNIFORM_TEXTURE_SAMPLER, texture.id);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

	@Override
	public Sprite clone() {
		return (Sprite) super.clone();
	}

}
