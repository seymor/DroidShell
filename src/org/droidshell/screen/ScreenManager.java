package org.droidshell.screen;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenManager {
	
	public static int width;
	public static int height;
	
	public static void setActivity(Activity a) {
		DisplayMetrics dM = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(dM);
		width = dM.widthPixels;
		height = dM.heightPixels;
	}

}
