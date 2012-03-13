package org.droidshell.opengl;

import android.opengl.GLES20;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class GLStateManager {
	
	public static void enableAlphaBlending(final int srcBlendingFactor, final int destBlendingFactor) {
		GLES20.glBlendFunc(srcBlendingFactor, destBlendingFactor);
		GLES20.glEnable(GLES20.GL_BLEND);
	}
	
	public static void disableAlphaBlending() {
		GLES20.glDisable(GLES20.GL_BLEND);
	}

}
