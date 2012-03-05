package org.droidshell.opengl.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

public class ShaderManager {
	
	private static final String TAG = ShaderManager.class.getName();
	private static Context context;
	
	private static HashMap<Integer, Integer> vertexShaders;
	private static HashMap<Integer, Integer> fragmentShaders;
	
	private static String readShader(InputStream inputStream) {
		
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
	
	private static void createShader(final int resourceId, String shader, final int glShaderType) {
		
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
		
		if (glShaderType == GLES20.GL_VERTEX_SHADER)
			vertexShaders.put(resourceId, shaderId);
		else if (glShaderType == GLES20.GL_FRAGMENT_SHADER)
			fragmentShaders.put(resourceId, shaderId);
		
	}
	
	public static void setContext(Context c) {
		context = c;
		
		vertexShaders = new HashMap<Integer, Integer>();
		fragmentShaders = new HashMap<Integer, Integer>();
	}
	
	public static void loadShader(final int resourceId, final int glShaderType) {
		
		InputStream inputStream = context.getResources().openRawResource(resourceId);
		String shader = readShader(inputStream);
		createShader(resourceId, shader, glShaderType);
		
	}
	
	public static int getVertexShader(final int resourceId) {
		if (vertexShaders.containsKey(resourceId))
			return vertexShaders.get(resourceId);

		Log.e(TAG, "Failed to get vertex shader: " + resourceId);
		return -1;
	}
	
	public static int getFragmentShader(final int resourceId) {
		if (fragmentShaders.containsKey(resourceId))
			return fragmentShaders.get(resourceId);

		Log.e(TAG, "Failed to get fragment shader: " + resourceId);
		return -1;
	}

}
