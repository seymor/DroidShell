package org.droidshell.input.sensor.handler;

import android.hardware.Sensor;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 03.05.2012
 */
public interface iAccuracyChangedHandler {
	
	public void onChange(Sensor sensor, int accuracy);

}
