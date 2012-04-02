package org.droidshell.music;

import java.util.HashMap;

import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class MusicLibrary {

	private static final String TAG = MusicLibrary.class.getName();

	private static HashMap<Integer, Music> musicList;

	public static void init() {
		musicList = new HashMap<Integer, Music>();
	}

	public static void put(int resourceId, Music music) {
		musicList.put(resourceId, music);
	}

	public static Music get(int resourceId) {
		if (musicList.containsKey(resourceId))
			return musicList.get(resourceId);

		Log.e(TAG, "Failed to get music: " + resourceId);
		return null;
	}

}
