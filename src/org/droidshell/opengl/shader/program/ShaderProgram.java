package org.droidshell.opengl.shader.program;

import android.opengl.GLES20;
import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ShaderProgram {
	
	private static final String TAG = ShaderProgram.class.getName();
	
	public int id;
	
	public final int vertexShaderId;
	public final int fragmentShaderId;
	
	public ShaderProgram(final int vertexShaderId, final int fragmentShaderId){
		
		this.vertexShaderId = vertexShaderId;
		this.fragmentShaderId = fragmentShaderId;
		
	}
	
	public ShaderProgram create() {
		
		this.id = GLES20.glCreateProgram();
		GLES20.glAttachShader(this.id, this.vertexShaderId);
		GLES20.glAttachShader(this.id, this.fragmentShaderId);
		
		GLES20.glLinkProgram(this.id);
		
		int[] linkStatus = new int[1];
		GLES20.glGetProgramiv(this.id, GLES20.GL_LINK_STATUS, linkStatus, 0);
		
		if(linkStatus[0] == GLES20.GL_FALSE)
		{
			Log.e(TAG, "Program link failed!");
			GLES20.glDeleteProgram(this.id);
			return null;
		}
		
		return this;
	}
	
	public void use() {
		GLES20.glUseProgram(this.id);
	}

}
