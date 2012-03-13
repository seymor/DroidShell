package org.droidshell.opengl.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.droidshell.exception.ClassNotInitializedException;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

public class ShaderFactory {
	
	private static final String TAG = ShaderFactory.class.getName();
	private static Context context;
	
	public static final int VERTEX_SHADER = GLES20.GL_VERTEX_SHADER;
	public static final int FRAGMENT_SHADER = GLES20.GL_FRAGMENT_SHADER;
	
	private static String read(InputStream inputStream) {
		
		StringBuffer shader = new StringBuffer();
		
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			
			while((line = reader.readLine()) != null)				
				shader.append(line + "\n");
			
		} catch (IOException e) {
			Log.e(TAG,"Tokenizer error: " + e.getMessage());
		} 
		
		return shader.toString();
		
	}
	
	private static void create(final int resourceId, String shader, final int glShaderType) {
		
		int shaderId = GLES20.glCreateShader(glShaderType);
		GLES20.glShaderSource(shaderId, shader);

		GLES20.glCompileShader(shaderId);

		int[] compileStatus = new int[1];
		GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
		
		if(compileStatus[0] == GLES20.GL_FALSE)
		{
			Log.e(TAG, "Shader compile error!");
			GLES20.glDeleteShader(shaderId);
			return;
		}
		
		try {
			ShaderDirectory.put(glShaderType, resourceId, shaderId);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
	}
	
	public static void init(Context c) {
		context = c;
		ShaderDirectory.init();
	}
	
	public static void build(final int resourceId, final int glShaderType) {
		if(context == null)
			throw new ClassNotInitializedException("Context not set!");
		
		InputStream inputStream = context.getResources().openRawResource(resourceId);
		String shader = read(inputStream);
		create(resourceId, shader, glShaderType);
		
	}	

}
