package org.droidshell.music;

import org.droidshell.exception.ResourceNotFoundException;

import android.util.SparseArray;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class MusicLibrary {

	@SuppressWarnings("unused")
	private static final String TAG = MusicLibrary.class.getName();

	private static SparseArray<Music> musicList;

	public static void onInit() {
		musicList = new SparseArray<Music>();
	}

	public static void put(int resourceId, Music music) {
		musicList.put(resourceId, music);
	}

	public static Music get(int resourceId) {
		Music music = musicList.get(resourceId);
		if(music == null)
			throw new ResourceNotFoundException("Music is not found in directory!");
		return music;
	}
	
	public static void remove(int resourceId) {
		musicList.remove(resourceId);
	}

}
