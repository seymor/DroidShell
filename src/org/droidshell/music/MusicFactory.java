package org.droidshell.music;

import org.droidshell.exception.ClassNotInitializedException;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class MusicFactory {

	@SuppressWarnings("unused")
	private static final String TAG = MusicFactory.class.getName();
	
	private static Context context;

	public static void onInit(Context c) {
		context = c;
		MusicLibrary.onInit();
	}

	public static void build(final int resourceId) {
		if (context == null)
			throw new ClassNotInitializedException("Context not set!");

		// after creating mediaPlayer it will be prepared automatically
		MediaPlayer mediaPlayer = MediaPlayer.create(context, resourceId);

		Music music = new Music(mediaPlayer);
		MusicLibrary.put(resourceId, music);

	}

}
