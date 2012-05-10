package org.droidshell.input.sensor;

import android.hardware.SensorManager;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 03.05.2012
 */
public enum SensorDelay {
	
	NORMAL(SensorManager.SENSOR_DELAY_NORMAL),
	UI(SensorManager.SENSOR_DELAY_UI),
	GAME(SensorManager.SENSOR_DELAY_GAME),
	FASTEST(SensorManager.SENSOR_DELAY_FASTEST);
	
	public final int delayType;
	
	private SensorDelay(final int delayType) {
		this.delayType = delayType;
	}

}
