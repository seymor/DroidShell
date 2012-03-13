package org.droidshell.opengl.texture;

import java.util.HashMap;

import android.util.Log;

public class TextureDirectory {
	
	private static final String TAG = TextureDirectory.class.getName();

	private static HashMap<Integer, Texture> textures;
	
	public static void init() {
		textures = new HashMap<Integer, Texture>();
	}
	
	public static void put(int resourceId, Texture texture) {		
		textures.put(resourceId, texture);
	}
	
	public static Texture get(Integer id) {		
		if (textures.containsKey(id))
			return textures.get(id);

		Log.e(TAG, "Failed to get texture " + id);
		return null;
	}

}
