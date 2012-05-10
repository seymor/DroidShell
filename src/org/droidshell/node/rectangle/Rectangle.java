package org.droidshell.node.rectangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.droidshell.engine.render.RenderContext;
import org.droidshell.lang.math.Color;
import org.droidshell.lang.math.Vector2D;
import org.droidshell.node.Node;
import org.droidshell.opengl.shader.program.input.ShaderProgramInput;
import org.droidshell.opengl.vertexbuffer.VertexBuffer;
import org.droidshell.opengl.vertexbuffer.VertexBufferDirectory;
import org.droidshell.opengl.vertexbuffer.VertexBufferFactory;

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

	public VertexBuffer positionBuffer;
	public VertexBuffer colorBuffer;

	public float width;
	public float height;
	public Color color;

	public Rectangle(float width, float height) {
		super();

		this.width = width;
		this.height = height;
		color = Color.WHITE;

		createPositionBuffer();
		createColorBuffer();
	}

	public Rectangle(float width, float height, Color color) {
		super();

		this.width = width;
		this.height = height;
		this.color = color;

		createPositionBuffer();
		createColorBuffer();
	}

	public Rectangle(Vector2D center, float width, float height) {
		super(center);
		this.width = width;
		this.height = height;
		color = Color.WHITE;

		createPositionBuffer();
		createColorBuffer();
	}

	public Rectangle(Vector2D center, float width, float height, Color color) {
		super(center);
		this.width = width;
		this.height = height;
		color = Color.WHITE;

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
		positionBuffer = new VertexBuffer(buffer, 2, GLES20.GL_FLOAT, false);

		try {
			VertexBufferFactory.build(vbId, positionBuffer);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	private void createColorBuffer() {
		String vbId = "COLOR" + ":" + color.toString();

		FloatBuffer buffer = this.createBuffer(this.createColorArray());
		colorBuffer = new VertexBuffer(buffer, 4, GLES20.GL_FLOAT, true);

		try {
			VertexBufferFactory.build(vbId, colorBuffer);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		colorBuffer = VertexBufferDirectory.get(vbId);
	}

	public void onRender(RenderContext renderContext) {
		ShaderProgramInput sI = renderContext.shaderInput;

		sI.prepareVertex(sI.ATTRIBUTE_POS, positionBuffer);
		sI.prepareVertex(sI.ATTRIBUTE_COLOR, colorBuffer);

		sI.prepareMatrix(sI.UNIFORM_MODELMATRIX, modelMatrix.toFloatArray());
		sI.prepareMatrix(sI.UNIFORM_VIEWPROJMATRIX,
				renderContext.camera.viewProjMatrix.toFloatArray());

		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

	@Override
	public Rectangle clone() {
		return (Rectangle) super.clone();
	}

	public boolean contains(Vector2D point) {

		Vector2D pos = getPosition();
		final float w = width / 2.0f;
		final float h = height / 2.0f;

		boolean c = (pos.x - w < point.x) && (pos.x + w < point.x)
				&& (pos.y + h > point.y) && (pos.y - h < point.y);

		return c;
	}

	public boolean contains(Rectangle rectangle) {

		Vector2D pos = getPosition();
		final float w = width / 2.0f;
		final float h = height / 2.0f;

		Vector2D rPos = rectangle.getPosition();
		final float rW = rectangle.width / 2.0f;
		final float rH = rectangle.height / 2.0f;

		final float distX = Math.abs(rPos.x - pos.x);
		final float distY = Math.abs(rPos.y - pos.y);

		// if the distance of the centers is less than the sum of the width/2
		// and height/2 the rectangles are covering each others
		boolean c = (distX < w + rW) && (distY < h + rH);

		return c;
	}
}
