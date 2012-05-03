package org.droidshell.input.touch.handler;

import android.view.MotionEvent;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 25.04.2012
 */
public interface iFlingEventHandler {

	public boolean onHandleEvent(MotionEvent e1, MotionEvent e2,
			float velocityX, float velocityY);

}
