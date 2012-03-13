package org.droidshell.node.sprite;

import java.nio.FloatBuffer;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Rectangle;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.texture.Texture;
import org.droidshell.opengl.texture.TextureDirectory;
import org.droidshell.opengl.vertexbuffer.VertexBuffer;
import org.droidshell.opengl.vertexbuffer.VertexBufferDirectory;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;
import org.droidshell.render.RenderContext;

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

	protected VertexBuffer texcoordBuffer;

	public int textureId;

	public Sprite(float width, float height, int textureId) {
		super(width, height);

		Texture texture = TextureDirectory.get(textureId);
		createTextureBuffer();

		this.textureId = texture.id;
	}

	public Sprite(float width, float height, Color color, int textureId) {
		super(width, height, color);

		Texture texture = TextureDirectory.get(textureId);
		createTextureBuffer();

		this.textureId = texture.id;
	}

	public Sprite(Vector2D center, float width, float height, int textureId) {
		super(center, width, height);

		Texture texture = TextureDirectory.get(textureId);
		createTextureBuffer();

		this.textureId = texture.id;
	}

	public Sprite(Vector2D center, float width, float height, Color color,
			int textureId) {
		super(center, width, height, color);

		Texture texture = TextureDirectory.get(textureId);
		createTextureBuffer();

		this.textureId = texture.id;
	}

	private float[] createTextureArray() {
		float[] textureArray = new float[] { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f };

		return textureArray;
	}

	private void createTextureBuffer() {
		String vbId = "TEXTURE" + ":" + "NORMAL";

		FloatBuffer buffer = this.createBuffer(this.createTextureArray());

		try {
			VertexBufferFactory.build(vbId, buffer, 2, GLES20.GL_FLOAT, true);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		texcoordBuffer = VertexBufferDirectory.get(vbId);
	}

	@Override
	public void render(RenderContext renderContext) {
		ShaderProgramInput sI = renderContext.shaderInput;

		sI.prepareVertex(sI.ATTRIBUTE_POS, positionBuffer);
		sI.prepareVertex(sI.ATTRIBUTE_COLOR, colorBuffer);
		sI.prepareVertex(sI.ATTRIBUTE_TEXCOORD, texcoordBuffer);

		Matrix modelViewProjMatrix = renderContext.camera.projMatrix.multiplyN(
				renderContext.camera.viewMatrix).multiplyN(modelMatrix);

		sI.prepareMatrix(sI.UNIFORM_MODELMATRIX, modelMatrix.toFloatArray());
		sI.prepareMatrix(sI.UNIFORM_MODELVIEWPROJMATRIX,
				modelViewProjMatrix.toFloatArray());
		sI.prepareTexture(sI.UNIFORM_TEXTURE_SAMPLER, textureId);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

}
