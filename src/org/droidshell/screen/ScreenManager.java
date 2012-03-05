package org.droidshell.screen;

import org.droidshell.math.Vector2D;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenManager {
	
	public static int width;
	public static int height;
	
	public static void init(Activity a) {
		DisplayMetrics dM = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(dM);
		width = dM.widthPixels;
		height = dM.heightPixels;
	}
	
	public static Vector2D convertCoordinatesToScreen2D(Vector2D coords) {
		
		return new Vector2D(coords.y*height/2.0f + height/2.0f,coords.x*width/2.0f + width/2.0f);
	}
	
	public static Vector2D convertCoordinatesToNorm(Vector2D coords) {
		
		return new Vector2D((coords.y - height/2.0f)/(height/2.0f), (coords.x - width/2.0f)/(width/2.0f));
	}
	
	/*
	 * normalizedCoords = {x1,y1, x2,y2, x3,y3 ...}
	 */
	public static float[] convertCoordinatesToScreen2D(float[] normalizedCoords) {
		
		float[] screenCoords = new float[normalizedCoords.length];
		
		for(int i = 0; i < normalizedCoords.length; i+=2) {
			screenCoords[i] = normalizedCoords[i+1]*height/2.0f + height/2.0f;
			screenCoords[i+1] = normalizedCoords[i]*width/2.0f + width/2.0f;
		}
		
		return screenCoords;
	}
	
	
	/*
	 * screenCoords = {x1,y1, x2,y2, x3,y3 ...}
	 */
	public static float[] convertCoordinatesToNorm(float[] screenCoords) {
		
		float[] normalizedCoords = new float[screenCoords.length];
		
		for(int i = 0; i < screenCoords.length; i+=2) {
			normalizedCoords[i] = (screenCoords[i+1] - height/2.0f)/(height/2.0f);
			normalizedCoords[i+1] = (screenCoords[i] - width/2.0f)/(width/2.0f);
		}
		
		return normalizedCoords;
	}

}
