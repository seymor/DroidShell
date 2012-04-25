package org.droidshell.input.touch;

import org.droidshell.engine.render.camera.Camera;
import org.droidshell.lang.math.Vector2D;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchController implements OnGestureListener, OnTouchListener,
		OnDoubleTapListener {

	private static final String TAG = TouchController.class.getName();

	public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;
	public static final int ACTION_UP = MotionEvent.ACTION_UP;
	public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;
	public static final int ACTION_CANCEL = MotionEvent.ACTION_CANCEL;
	public static final int ACTION_OUTSIDE = MotionEvent.ACTION_OUTSIDE;

	@SuppressWarnings("unused")
	private Context context;
	public Camera camera;
	public GestureDetector gestureDetector;

	public TouchController(Context context) {
		this.context = context;
		gestureDetector = new GestureDetector(context, this);
		Log.d(TAG, gestureDetector.toString());
	}

	public boolean onTouch(View vUnused, MotionEvent e) {
		if (camera != null) {
			Vector2D v = camera.convertScreenToWorldCoordinates(e.getX(),
					e.getY());
			Log.d(TAG, "onTouch: " + String.valueOf(v.x) + ":" + String.valueOf(v.y));
		}

		return gestureDetector.onTouchEvent(e);
	}

	public boolean onDown(MotionEvent e) {
		Log.d(TAG, "onDown");
		return true;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.d(TAG, "onFling");
		return true;
	}

	public void onLongPress(MotionEvent e) {
		Log.d(TAG, "onLongPress");
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.d(TAG, "onScroll");
		return true;
	}

	public void onShowPress(MotionEvent e) {
		Log.d(TAG, "onShowPress");
	}

	public boolean onSingleTapUp(MotionEvent e) {
		Log.d(TAG, "onSingleTapUp");
		return false;
	}

	public boolean onDoubleTap(MotionEvent e) {
		Log.d(TAG, "onDoubleTap");
		return false;
	}

	public boolean onDoubleTapEvent(MotionEvent e) {
		Log.d(TAG, "onDoubleTapEvent");
		return false;
	}

	public boolean onSingleTapConfirmed(MotionEvent e) {
		Log.d(TAG, "onSingleTapConfirmed");
		return false;
	}

}
