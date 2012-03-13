package org.droidshell.node.rectangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Matrix;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.vertexbuffer.VertexBuffer;
import org.droidshell.opengl.vertexbuffer.VertexBufferDirectory;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;
import org.droidshell.render.RenderContext;

import android.opengl.GLES20;
import android.util.Log;

//	  A	*------* B
//		|      |
//		|      |
//	  C	*------* D

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Rectangle extends Node {

	private static final String TAG = Rectangle.class.getName();

	protected VertexBuffer positionBuffer;
	protected VertexBuffer colorBuffer;

	public float width;
	public float height;

	public Rectangle(float width, float height) {
		super();

		this.width = width;
		this.height = height;

		createPositionBuffer();
		createColorBuffer();
	}

	public Rectangle(float width, float height, Color color) {
		super(color);

		this.width = width;
		this.height = height;

		createPositionBuffer();
		createColorBuffer();
	}

	public Rectangle(Vector2D center, float width, float height) {
		super(center);
		this.width = width;
		this.height = height;

		createPositionBuffer();
		createColorBuffer();
	}

	public Rectangle(Vector2D center, float width, float height, Color color) {
		super(center, color);
		this.width = width;
		this.height = height;

		createPositionBuffer();
		createColorBuffer();
	}

	private float[] createPositionArray() {
		float[] vertexArray = new float[] { coords.x - width / 2,
				coords.y + height / 2, coords.x + width / 2,
				coords.y + height / 2, coords.x - width / 2,
				coords.y - height / 2, coords.x + width / 2,
				coords.y - height / 2 };

		return vertexArray;
	}

	private float[] createColorArray() {
		float[] colorArray = new float[] { color.r, color.g, color.b, color.a,
				color.r, color.g, color.b, color.a, color.r, color.g, color.b,
				color.a, color.r, color.g, color.b, color.a, };

		return colorArray;
	}

	protected FloatBuffer createBuffer(float[] array) {

		FloatBuffer buffer = null;

		ByteBuffer vbb = ByteBuffer.allocateDirect(array.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		buffer = vbb.asFloatBuffer();
		buffer.put(array);
		buffer.position(0);

		return buffer;
	}

	private void createPositionBuffer() {
		String vbId = "POSITION" + ":" + coords.toString() + ":" + "(" + width
				+ "," + height + ")";

		FloatBuffer buffer = this.createBuffer(this.createPositionArray());

		try {
			VertexBufferFactory.build(vbId, buffer, 2, GLES20.GL_FLOAT, false);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		positionBuffer = VertexBufferDirectory.get(vbId);
	}

	private void createColorBuffer() {
		String vbId = "COLOR" + ":" + color.toString();

		FloatBuffer buffer = this.createBuffer(this.createColorArray());

		try {
			VertexBufferFactory.build(vbId, buffer, 4, GLES20.GL_FLOAT, true);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		colorBuffer = VertexBufferDirectory.get(vbId);
	}

	@Override
	public void translate(float tX, float tY) {
		this.modelMatrix.translate(tX, tY);
	}

	@Override
	public void scale(float sX, float sY) {
		this.modelMatrix.scale(sX, sY);
	}

	@Override
	public void rotate(float angle) {
		this.modelMatrix.rotate(angle);
	}

	@Override
	public void update(long gameTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(RenderContext renderContext) {
		ShaderProgramInput sI = renderContext.shaderInput;

		sI.prepareVertex(sI.ATTRIBUTE_POS, positionBuffer);
		sI.prepareVertex(sI.ATTRIBUTE_COLOR, colorBuffer);

		Matrix modelViewProjMatrix = renderContext.camera.projMatrix.multiplyN(
				renderContext.camera.viewMatrix).multiplyN(modelMatrix);

		sI.prepareMatrix(sI.UNIFORM_MODELMATRIX, modelMatrix.toFloatArray());
		sI.prepareMatrix(sI.UNIFORM_MODELVIEWPROJMATRIX,
				modelViewProjMatrix.toFloatArray());

		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

}
