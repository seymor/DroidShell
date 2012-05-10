package org.droidshell.input.sensor.handler;

import android.hardware.SensorEvent;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 03.05.2012
 */
public interface iSensorChangedHandler {
	
	public void onChange(SensorEvent event);

}
