package org.droidshell.input.touch;

import java.util.ArrayList;

import org.droidshell.input.touch.handler.iDoubleTapEventHandler;
import org.droidshell.input.touch.handler.iDownEventHandler;
import org.droidshell.input.touch.handler.iFlingEventHandler;
import org.droidshell.input.touch.handler.iLongPressEventHandler;
import org.droidshell.input.touch.handler.iScrollEventHandler;
import org.droidshell.input.touch.handler.iShowPressEventHandler;
import org.droidshell.input.touch.handler.iSingleTapUpEventHandler;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 25.04.2012
 */
public class TouchController implements OnGestureListener, OnTouchListener,
		OnDoubleTapListener {

	private static final String TAG = TouchController.class.getName();

	public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;
	public static final int ACTION_UP = MotionEvent.ACTION_UP;
	public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;
	public static final int ACTION_CANCEL = MotionEvent.ACTION_CANCEL;
	public static final int ACTION_OUTSIDE = MotionEvent.ACTION_OUTSIDE;

	private Context context;
	public GestureDetector gestureDetector;

	public ArrayList<iDownEventHandler> downEvents;
	public ArrayList<iScrollEventHandler> scrollEvents;
	public ArrayList<iFlingEventHandler> flingEvents;
	public ArrayList<iLongPressEventHandler> longPressEvents;
	public ArrayList<iShowPressEventHandler> showPressEvents;
	public ArrayList<iSingleTapUpEventHandler> singleTapUpEvents;
	public ArrayList<iDoubleTapEventHandler> doubleTapEvents;

	public TouchController(Context context) {
		this.context = context;
		onInit();
	}

	private void onInit() {
		downEvents = new ArrayList<iDownEventHandler>();
		scrollEvents = new ArrayList<iScrollEventHandler>();
		flingEvents = new ArrayList<iFlingEventHandler>();
		longPressEvents = new ArrayList<iLongPressEventHandler>();
		showPressEvents = new ArrayList<iShowPressEventHandler>();
		singleTapUpEvents = new ArrayList<iSingleTapUpEventHandler>();
		doubleTapEvents = new ArrayList<iDoubleTapEventHandler>();

		gestureDetector = new GestureDetector(context, this);
	}

	public boolean onTouch(View vUnused, MotionEvent e) {
		return gestureDetector.onTouchEvent(e);
	}

	public boolean onDown(MotionEvent e) {
		for (int i = 0; i < downEvents.size(); i++)
			downEvents.get(i).onHandleEvent(e);
		Log.d(TAG, "onDown");
		return true;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		for (int i = 0; i < flingEvents.size(); i++)
			flingEvents.get(i).onHandleEvent(e1, e2, velocityX, velocityY);
		Log.d(TAG, "onFling");
		return true;
	}

	public void onLongPress(MotionEvent e) {
		for (int i = 0; i < longPressEvents.size(); i++)
			longPressEvents.get(i).onHandleEvent(e);
//		Log.d(TAG, "onLongPress");
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		for (int i = 0; i < scrollEvents.size(); i++)
			scrollEvents.get(i).onHandleEvent(e1, e2, distanceX, distanceY);
		Log.d(TAG, "onScroll");
		return true;
	}

	public void onShowPress(MotionEvent e) {
		for (int i = 0; i < showPressEvents.size(); i++)
			showPressEvents.get(i).onHandleEvent(e);
//		Log.d(TAG, "onShowPress");
	}

	public boolean onSingleTapUp(MotionEvent e) {
		for (int i = 0; i < singleTapUpEvents.size(); i++)
			singleTapUpEvents.get(i).onHandleEvent(e);
//		Log.d(TAG, "onSingleTapUp");
		return false;
	}

	public boolean onDoubleTap(MotionEvent e) {
		for (int i = 0; i < doubleTapEvents.size(); i++)
			doubleTapEvents.get(i).onHandleEvent(e);
		Log.d(TAG, "onDoubleTap");
		return false;
	}

	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO what's this???
//		Log.d(TAG, "onDoubleTapEvent");
		return false;
	}

	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO what's this???
//		Log.d(TAG, "onSingleTapConfirmed");
		return false;
	}

}
