package org.droidshell.node;

import org.droidshell.math.Color;
import org.droidshell.math.Vector2D;
import org.droidshell.opengl.shader.ShaderAttributeManager;
import org.droidshell.opengl.texture.Texture;
import org.droidshell.opengl.vbo.VertexBufferObjectFactory;
import android.opengl.GLES20;
import android.util.Log;

public class Sprite extends Rectangle {

	private static final String TAG = Sprite.class.getName();
	private static final String VBOSPRITE = "DS_SPRITE";
	public static final int TEXTURE_BUFFER_ID = 2;

	private Texture texture;

	public Sprite(Vector2D center, float width, float height, Color color,
			Texture texture) {
		super(center, width, height, color, true);

		createVBO(center, width, height, color, texture);

		this.texture = texture;
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

		vbo = VertexBufferObjectFactory.getVBO(vboId);

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
			ShaderAttributeManager.addAttribute(program.id, attributeName);
			vbo.setAttributeHandler(bufferType,
					ShaderAttributeManager.getAttributeHandler(attributeName));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void bindShaderUniform(int uniformType, String uniformName) throws Exception {

		if (program == null)
			throw new Exception("ShaderProgram not set for this Node!");

		try {
			ShaderAttributeManager.addUniform(program.id, uniformName);
			vbo.setUniformHandler(uniformType,
					ShaderAttributeManager.getUniformHandler(uniformName));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void draw() {
		vbo.prepare();
		vbo.prepareModelMatrix(this.modelMatrix.toFloatArray());

		try {
			vbo.prepareTexture(this.texture.id);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		vbo.draw(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

}
