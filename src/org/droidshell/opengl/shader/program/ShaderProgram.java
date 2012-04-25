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
		
		id = GLES20.glCreateProgram();
		GLES20.glAttachShader(id, vertexShaderId);
		GLES20.glAttachShader(id, fragmentShaderId);
		
		GLES20.glLinkProgram(id);
		
		int[] linkStatus = new int[1];
		GLES20.glGetProgramiv(id, GLES20.GL_LINK_STATUS, linkStatus, 0);
		
		if(linkStatus[0] == GLES20.GL_FALSE)
		{
			Log.e(TAG, "Program link failed!");
			GLES20.glDeleteProgram(id);
			return null;
		}
		
		return this;
	}
	
	public void use() {
		GLES20.glUseProgram(id);
	}

}
