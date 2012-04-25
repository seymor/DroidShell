package org.droidshell.screen;

import org.droidshell.lang.math.Color;

import android.app.Activity;
import android.content.pm.ActivityInfo;
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

	public static final int LANDSCAPE = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
	public static final int PORTRAIT = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

	private static Activity activity;

	public static int orientation;
	public static int width;
	public static int height;

	public static void onInit(Activity a) {
		activity = a;
		DisplayMetrics dM = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dM);
		width = dM.widthPixels;
		height = dM.heightPixels;
	}

	public static void fullScreen() {
		/*
		 * You can set this in AndroidManifest:
		 * android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
		 */
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public static void ignoreOrientationChanges() {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
	}

	public static void setOrientation(int screenConstant) {

		/*
		 * You can set this in AndroidManifest:
		 * android:screenOrientation="landscape"
		 */
		orientation = screenConstant;
		activity.setRequestedOrientation(screenConstant);
	}

	public static void viewPort(int x, int y, int width, int height) {
		GLES20.glViewport(x, y, width, height);
	}

	public static void clearColor(Color color) {
		GLES20.glClearColor(color.r, color.g, color.b, color.a);
	}

	public static void clearFrame(int mask) {
		GLES20.glClear(mask);
	}

}
