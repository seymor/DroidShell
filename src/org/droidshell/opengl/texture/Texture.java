package org.droidshell.opengl.texture;

import android.graphics.Bitmap;

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
