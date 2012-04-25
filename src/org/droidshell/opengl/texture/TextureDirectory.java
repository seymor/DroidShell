package org.droidshell.opengl.texture;

import org.droidshell.exception.ResourceNotFoundException;

import android.util.SparseArray;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class TextureDirectory {
	
	@SuppressWarnings("unused")
	private static final String TAG = TextureDirectory.class.getName();

	private static SparseArray<Texture> textures;
	
	public static void onInit() {
		textures = new SparseArray<Texture>();
	}
	
	public static void put(int resourceId, Texture texture) {		
		textures.put(resourceId, texture);
	}
	
	public static Texture get(int id) {
		Texture texture = textures.get(id);
		if(texture == null)
			throw new ResourceNotFoundException("Texture is not found in directory!");
		return textures.get(id);
	}
	
	public static void remove(int id) {
		textures.remove(id);
	}

}
