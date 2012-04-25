package org.droidshell.opengl.shader.program.input;

import org.droidshell.exception.ShaderProgramInputBindingException;
import org.droidshell.lang.DSInteger;
import org.droidshell.opengl.shader.program.ShaderProgram;
import org.droidshell.opengl.vertexbuffer.VertexBuffer;

import android.opengl.GLES20;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderProgramInput {

	@SuppressWarnings("unused")
	private static final String TAG = ShaderProgramInput.class.getName();

	public ShaderProgram shaderProgram;

	public DSInteger ATTRIBUTE_POS = new DSInteger();
	public DSInteger ATTRIBUTE_COLOR = new DSInteger();
	public DSInteger ATTRIBUTE_TEXCOORD = new DSInteger();

	public DSInteger UNIFORM_MODELMATRIX = new DSInteger();
	public DSInteger UNIFORM_MODELVIEWMATRIX = new DSInteger();
	public DSInteger UNIFORM_VIEWPROJMATRIX = new DSInteger();
	public DSInteger UNIFORM_MODELVIEWPROJMATRIX = new DSInteger();
	public DSInteger UNIFORM_TEXTURE_SAMPLER = new DSInteger();

	public ShaderProgramInput(ShaderProgram shaderProgram) {
		this.shaderProgram = shaderProgram;
	}

	public void bindAttribute(DSInteger attributeInput, String name) {
		int handler = GLES20.glGetAttribLocation(shaderProgram.id, name);

		if (handler >= 0)
			attributeInput.value = handler;
		else {
			throw new ShaderProgramInputBindingException(
					"Cannot find attribute with name: " + name);
		}
	}

	public void bindUniform(DSInteger uniformInput, String name) {
		int handler = GLES20.glGetUniformLocation(shaderProgram.id, name);

		if (handler >= 0)
			uniformInput.value = handler;
		else {
			throw new ShaderProgramInputBindingException(
					"Cannot find uniform with name: " + name);
		}
	}

	public void prepareVertex(DSInteger attributeInput, VertexBuffer vB) {
		GLES20.glVertexAttribPointer(attributeInput.toInteger(), vB.size,
				vB.glType, vB.isNormalized, 0, vB.buffer);
		GLES20.glEnableVertexAttribArray(attributeInput.toInteger());
	}

	public void prepareTexture(DSInteger uniformInput, int textureId) {
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
		GLES20.glUniform1i(uniformInput.toInteger(), 0);
	}

	public void prepareMatrix(DSInteger uniformInput, float[] matrix) {
		GLES20.glUniformMatrix4fv(uniformInput.toInteger(), 1, false, matrix, 0);
	}

}
