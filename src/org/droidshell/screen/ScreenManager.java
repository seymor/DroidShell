package org.droidshell.screen;

import org.droidshell.exception.ClassNotInitializedException;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

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
		
		if(activity == null)
			throw new ClassNotInitializedException("Activity not set!");
		/*
		 * You can set this in AndroidManifest:
		 * android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
		 */
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}
