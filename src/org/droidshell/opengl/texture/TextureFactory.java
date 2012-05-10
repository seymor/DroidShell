package org.droidshell.opengl.texture;

import org.droidshell.exception.ClassNotInitializedException;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class TextureFactory {

	@SuppressWarnings("unused")
	private static final String TAG = TextureFactory.class.getName();

	public static final int NEAREST = GLES20.GL_NEAREST;
	public static final int LINEAR = GLES20.GL_LINEAR;
	public static final int CLAMP_TO_EDGE = GLES20.GL_CLAMP_TO_EDGE;
	public static final int REPEAT = GLES20.GL_REPEAT;

	private static Context context;

	public static void onInit(Context c) {
		context = c;
		TextureDirectory.onInit();
	}

	public static void build(final int resourceId, final int minFilter,
			final int magFilter, final int wrapS, final int wrapT) {
		if (context == null)
			throw new ClassNotInitializedException("Context not set!");

		int[] textureArrayId = new int[1];

		GLES20.glGenTextures(1, textureArrayId, 0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureArrayId[0]);

		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
				GLES20.GL_TEXTURE_MIN_FILTER, minFilter);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
				GLES20.GL_TEXTURE_MAG_FILTER, magFilter);

		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
				wrapS);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
				wrapT);

		Texture texture = new Texture(BitmapFactory.decodeResource(
				context.getResources(), resourceId), textureArrayId[0]);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, texture.bitmap, 0);

		TextureDirectory.put(resourceId, texture);

	}
	
	public static void build(final int resourceId) {
		if (context == null)
			throw new ClassNotInitializedException("Context not set!");

		int[] textureArrayId = new int[1];

		GLES20.glGenTextures(1, textureArrayId, 0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureArrayId[0]);		
		
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
				GLES20.GL_TEXTURE_MIN_FILTER, TextureFactory.NEAREST);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,
				GLES20.GL_TEXTURE_MAG_FILTER, TextureFactory.LINEAR);

		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
				TextureFactory.CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
				TextureFactory.CLAMP_TO_EDGE);

		Texture texture = new Texture(BitmapFactory.decodeResource(
				context.getResources(), resourceId), textureArrayId[0]);

		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, texture.bitmap, 0);

		TextureDirectory.put(resourceId, texture);

	}

}
