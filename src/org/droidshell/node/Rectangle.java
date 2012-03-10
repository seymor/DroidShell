package org.droidshell.node;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.droidshell.math.Color;
import org.droidshell.math.Matrix;
import org.droidshell.math.Vector2D;
import org.droidshell.opengl.shader.ShaderAttributeManager;
import org.droidshell.opengl.shader.ShaderProgram;
import org.droidshell.opengl.vbo.VertexBufferObject;
import org.droidshell.opengl.vbo.VertexBufferObjectFactory;

import android.opengl.GLES20;
import android.util.Log;

//	  A	*------* B
//		|      |
//		|      |
//	  C	*------* D

public class Rectangle extends Node {

	private static final String TAG = Rectangle.class.getName();
	private static final String VBORECT = "DS_RECT";
	public static final int POSITION_BUFFER_ID = 0;
	public static final int COLOR_BUFFER_ID = 1;

	protected VertexBufferObject vbo;
	protected ShaderProgram program;

	protected String vboId;

	public float width;
	public float height;

	public Rectangle(Vector2D center, float width, float height, Color color) {
		super(center);
		this.width = width;
		this.height = height;
		
		createVBO(center, width, height, color);
	}

	public Rectangle(Vector2D center, float width, float height, Color color,
			boolean noVBO) {
		super(center);
		
		this.width = width;
		this.height = height;
		
		if (!noVBO) 
			createVBO(center, width, height, color);

	}

	protected float[] createVertexArray(Vector2D center, float width,
			float height) {
		float[] vertexArray = new float[] { center.x - width / 2,
				center.y + height / 2, center.x + width / 2,
				center.y + height / 2, center.x - width / 2,
				center.y - height / 2, center.x + width / 2,
				center.y - height / 2 };

		return vertexArray;
	}

	protected float[] createColorArray(Color color) {
		float[] colorArray = new float[] { color.r, color.g, color.b, color.a,
				color.r, color.g, color.b, color.a, color.r, color.g, color.b,
				color.a, color.r, color.g, color.b, color.a, };

		return colorArray;
	}
	
	protected void createVBO(Vector2D center, float width, float height, Color color) {
		vboId = VBORECT + ":" + center.toString() + ":(" + width + "," + height
				+ "):" + color.toString() + ":(TEXT_NO)";

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
				GLES20.GL_FLOAT, true);
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

	public void addShaderProgram(ShaderProgram program) {
		this.program = program;
	}

	/*
	 * bufferType = [position, color]
	 */
	public void bindShaderAttribute(int bufferType, String attributeName)
			throws Exception {

		if (bufferType > 2 || bufferType < 0) {
			throw new Exception("Unknown buffer type! (0,1,2 are valid)");
		}
		
		if (program == null)
			throw new Exception("ShaderProgram not set for this Rectangle!");

		try {
			ShaderAttributeManager.addAttribute(program.id, attributeName);
			vbo.setAttributeHandler(bufferType,
					ShaderAttributeManager.getAttributeHandler(attributeName));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void draw() {
		vbo.prepare();
		vbo.prepareModelMatrix(this.modelMatrix.toFloatArray());
		vbo.draw(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

	@Override
	public void translate(float tX, float tY) {
		this.modelMatrix.add(Matrix.getTranslationMatrix(tX, tY));
	}

	@Override
	public void scale(float sX, float sY) {
		this.modelMatrix.add(Matrix.getScaleMatrix(sX, sY));
	}

	@Override
	public void rotate(float angleD) {
		this.modelMatrix.add(Matrix.getRotationMatrix(angleD));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
