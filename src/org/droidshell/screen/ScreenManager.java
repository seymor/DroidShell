package org.droidshell.screen;

import org.droidshell.exception.ClassNotInitializedException;
import org.droidshell.lang.math.Color;

import android.app.Activity;
import android.opengl.GLES20;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class ScreenManager {

	private static Activity activity;

	public static int width = 480;
	public static int height = 800;

	public static void init(Activity a) {
		activity = a;
		DisplayMetrics dM = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dM);
		width = dM.widthPixels;
		height = dM.heightPixels;
	}

	public static void fullScreen() {

		if (activity == null)
			throw new ClassNotInitializedException("Activity not set!");
		/*
		 * You can set this in AndroidManifest:
		 * android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
		 */
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public static void viewPort(int x, int y, int width, int height) {
		GLES20.glViewport(x, y, width, height);
	}
	
	public static void clearFrame(Color color, int mask) {
		GLES20.glClearColor(color.r, color.g, color.b, color.a);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
	}

}
