package org.droidshell.engine;

import org.droidshell.exception.EngineException;

import android.app.Activity;
import android.util.Log;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 04.04.2012
 */
public class FixStepEngine extends Engine {
	//TODO SZAR!!!!
	private static final String TAG = FixStepEngine.class.getName();

	private long sleepTime;
	private int framePeriod;

	public int fps;

	public FixStepEngine(Activity activity, int fps) {
		super(activity);
		this.fps = fps;
		sleepTime = 0;
		framePeriod = 1000 / fps;
	}

	public void onUpdate() throws EngineException {
		if (isRunning) {

			final long elapsedTime = timeElapsed();
			lastTick += elapsedTime;

			sleepTime = framePeriod - elapsedTime;

			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);

					scene.onUpdate(elapsedTime + sleepTime);
					renderContext.camera.onUpdate(elapsedTime + sleepTime);
				} catch (InterruptedException e) {
					Log.e(TAG, e.getMessage());
				}
			} else {

				scene.onUpdate(elapsedTime);
				renderContext.camera.onUpdate(elapsedTime);

			}

		}
	}
}
