package org.droidshell.opengl.texture;

import android.graphics.Bitmap;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class Texture {

	public Bitmap bitmap;
	public int id;
	
	public Texture(Bitmap bitmap, int id){
		
		this.bitmap = bitmap;
		this.id = id;
		
	}
	
	public String toString() {
		return "(TEXT_" + id + ")";
	}
	
}
