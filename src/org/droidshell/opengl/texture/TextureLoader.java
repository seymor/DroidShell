package org.droidshell.opengl.texture;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.droidshell.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class TextureLoader {

	private static final String TAG = TextureLoader.class.getName();

	private static HashMap<Integer, Texture> textures;
	private static Context context;

	public static void setContext(Context c) {
		context = c;
	}

	public static void loadImages() {

		textures = new HashMap<Integer, Texture>();

		R.drawable drawableResources = new R.drawable();
		Class<R.drawable> c = R.drawable.class;
		Field[] fields = c.getDeclaredFields();

		int[] textureArrayID = new int[1];
		int resourceId;

		for (int i = 0; i < fields.length; i++) {

			try {
				resourceId = fields[i].getInt(drawableResources);

				GLES20.glGenTextures(1, textureArrayID, 0);

				GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureArrayID[0]);

				GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
						GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
				GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
						GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

				Texture texture = new Texture(BitmapFactory.decodeResource(
						context.getResources(), resourceId), textureArrayID[0]);

				GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, texture.bitmap, 0);

				textures.put(resourceId, texture);

			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
				continue;
			}
		}

	}

	public static Texture getTexture(Integer id) {
		if (textures.containsKey(id))
			return textures.get(id);

		Log.e(TAG, "Failed to get texture " + id);
		return null;
	}

}
