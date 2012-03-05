package org.droidshell.opengl.shader;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderProgram {
	
	private static final String TAG = ShaderProgram.class.getName();
	
	public int id;
	
	public final int vertexShaderId;
	public final int fragmentShaderId;
	
	public ShaderProgram(final int vertexShaderId, final int fragmentShaderId){
		
		this.vertexShaderId = vertexShaderId;
		this.fragmentShaderId = fragmentShaderId;
		
	}
	
	public int create() {
		
		this.id = GLES20.glCreateProgram();
		GLES20.glAttachShader(this.id, this.vertexShaderId);
		GLES20.glAttachShader(this.id, this.fragmentShaderId);
		
		GLES20.glLinkProgram(this.id);
		
		int[] linkStatus = new int[1];
		GLES20.glGetProgramiv(this.id, GLES20.GL_LINK_STATUS, linkStatus, 0);
		
		if(linkStatus[0] == GLES20.GL_FALSE)
		{
			Log.d(TAG, "Program link failed!");
			GLES20.glDeleteProgram(this.id);
			return -1;
		}
		
		return this.id;
	}
	
	public void use() {
		GLES20.glUseProgram(this.id);
	}

}
