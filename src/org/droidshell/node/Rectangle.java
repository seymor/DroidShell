package org.droidshell.node;

import org.droidshell.math.Vector2D;
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
	
	public float width;
	public float height;
	
	public VertexBufferObject vbo;
	public ShaderProgram program;
	
	public Rectangle(Vector2D center, float width, float height) {
		super(center);
		this.width = width;
		this.height = height;
	}
	
	public VertexBufferObject createBuffer(String name) {
		try {
			VertexBufferObjectFactory.build(name);
		} catch (Exception e) {
			Log.e(TAG, "Failed to create VBO: " + e.getMessage());
		}
		
		this.vbo = VertexBufferObjectFactory.getVBO(name);
		
		return this.vbo;
	}
	
	
	public void draw() {
		vbo.draw(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}

}
