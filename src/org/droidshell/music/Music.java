package org.droidshell.music;

import android.media.MediaPlayer;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.04.2012
 */
public class Music {

	public MediaPlayer mediaPlayer;

	public Music(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public void play() {
		mediaPlayer.start();
	}

	public void replay() {
		mediaPlayer.stop();
		mediaPlayer.start();
	}

	public void stop() {
		mediaPlayer.stop();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void resume() {
		mediaPlayer.start();
	}

	public void seekTo(int milliSeconds) {
		mediaPlayer.seekTo(milliSeconds);
	}

	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	public boolean isLooping() {
		return mediaPlayer.isLooping();
	}

	public void setLooping(boolean isLooping) {
		mediaPlayer.setLooping(isLooping);
	}

	public void setVolume(float fixVolume) {
		mediaPlayer.setVolume(fixVolume, fixVolume);
	}

	public void setVolume(float leftVolume, float rightVolume) {
		mediaPlayer.setVolume(leftVolume, rightVolume);
	}

}
