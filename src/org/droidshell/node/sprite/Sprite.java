package org.droidshell.node.sprite;

import org.droidshell.math.Color;
import org.droidshell.math.Matrix;
import org.droidshell.math.Vector2D;
import org.droidshell.node.Rectangle;
import org.droidshell.opengl.shader.program.input.ShaderProgramInputManager;
import org.droidshell.opengl.texture.Texture;
import org.droidshell.opengl.texture.TextureDirectory;
import org.droidshell.opengl.vbo.VertexBufferObjectDirectory;
import org.droidshell.opengl.vbo.VertexBufferObjectFactory;
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
	private static final String VBOSPRITE = "DS_SPRITE";
	public static final int TEXTURE_BUFFER_ID = 2;

	public int textureId;

	public Sprite(float width, float height, int textureId) {
		super(width, height, Color.WHITE, true);

		Texture texture = TextureDirectory.get(textureId);
		createVBO(coords, width, height, Color.WHITE, texture);

		this.textureId = texture.id;
	}
	
	public Sprite(float width, float height, int textureId, boolean noVBO) {
		super(width, height, Color.WHITE, true);

		Texture texture = TextureDirectory.get(textureId);
		if (!noVBO)
			createVBO(coords, width, height, Color.WHITE, texture);

		this.textureId = texture.id;
	}

	public Sprite(float width, float height, Color color, int textureId) {
		super(width, height, color, true);

		Texture texture = TextureDirectory.get(textureId);
		createVBO(coords, width, height, color, texture);

		this.textureId = texture.id;
	}

	public Sprite(Vector2D center, float width, float height, int textureId) {
		super(center, width, height, Color.WHITE, true);

		Texture texture = TextureDirectory.get(textureId);
		createVBO(center, width, height, Color.WHITE, texture);

		this.textureId = texture.id;
	}

	public Sprite(Vector2D center, float width, float height, Color color,
			int textureId) {
		super(center, width, height, color, true);

		Texture texture = TextureDirectory.get(textureId);
		createVBO(center, width, height, color, texture);

		this.textureId = texture.id;
	}

	protected float[] createTextureArray() {
		float[] textureArray = new float[] { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f };

		return textureArray;
	}

	protected void createVBO(Vector2D center, float width, float height,
			Color color, Texture texture) {
		vboId = VBOSPRITE + ":" + center.toString() + ":(" + width + ","
				+ height + "):" + color.toString() + ":" + texture.toString();

		try {
			VertexBufferObjectFactory.build(vboId);
		} catch (Exception e) {
			Log.e(TAG, "Failed to create VBO: " + e.getMessage());
		}

		vbo = VertexBufferObjectDirectory.get(vboId);

		vbo.add(POSITION_BUFFER_ID,
				this.createBuffer(this.createVertexArray(center, width, height)),
				2, GLES20.GL_FLOAT, false)
				.add(COLOR_BUFFER_ID,
						this.createBuffer(this.createColorArray(color)), 4,
						GLES20.GL_FLOAT, true)
				.add(TEXTURE_BUFFER_ID,
						this.createBuffer(this.createTextureArray()), 2,
						GLES20.GL_FLOAT, true);
	}

	/*
	 * bufferType = [position, color, texture]
	 */
	public void bindShaderAttribute(int bufferType, String attributeName)
			throws Exception {

		if (bufferType > 2 || bufferType < 0) {
			throw new Exception("Unknown buffer type! (0,1,2 are valid)");
		}

		if (program == null)
			throw new Exception("ShaderProgram not set for this Sprite!");

		try {
			ShaderProgramInputManager.addAttribute(program.id, attributeName);
			vbo.setAttributeHandler(bufferType, ShaderProgramInputManager
					.getAttributeHandler(attributeName));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void bindShaderUniform(int uniformType, String uniformName)
			throws Exception {

		if (program == null)
			throw new Exception("ShaderProgram not set for this Node!");

		try {
			ShaderProgramInputManager.addUniform(program.id, uniformName);
			vbo.setUniformHandler(uniformType,
					ShaderProgramInputManager.getUniformHandler(uniformName));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@Override
	public void render(RenderContext renderContext) {
		vbo.prepare();

		GLES20.glUniformMatrix4fv(renderContext.modelMatrixHandler, 1, false,
				modelMatrix.toFloatArray(), 0);

		Matrix modelViewProjMatrix = renderContext.camera.projMatrix.multiplyN(
				renderContext.camera.viewMatrix).multiplyN(modelMatrix);
		
		GLES20.glUniformMatrix4fv(renderContext.modelViewProjMatrixHandler, 1, false,
				modelViewProjMatrix.toFloatArray(), 0);

		try {
			vbo.prepareTexture(this.textureId);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		vbo.draw(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

}
