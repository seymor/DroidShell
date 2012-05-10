package org.droidshell.input.sensor;

import java.util.ArrayList;

import org.droidshell.input.sensor.handler.iAccuracyChangedHandler;
import org.droidshell.input.sensor.handler.iSensorChangedHandler;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.util.SparseArray;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 03.05.2012
 */
public class SensorController implements SensorEventListener {

	private static final String TAG = SensorController.class.getName();
	
	public static final int ACCELEROMETER = Sensor.TYPE_ACCELEROMETER;
	public static final int GRAVITY = Sensor.TYPE_GRAVITY;
	public static final int GYROSCOPE = Sensor.TYPE_GYROSCOPE;
	public static final int ORIENTATION = Sensor.TYPE_ORIENTATION;
	
	@SuppressWarnings("unused")
	private Context context;
	private final SensorManager sensorManager;
	
	public SparseArray<Sensor> sensors;
	public ArrayList<iAccuracyChangedHandler> accuracyChangedEvents;
	public ArrayList<iSensorChangedHandler> sensorChangedEvents;
	
	public SensorController(Context context) {
		this.context = context;
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		
		sensors = new SparseArray<Sensor>();
		accuracyChangedEvents = new ArrayList<iAccuracyChangedHandler>();
		sensorChangedEvents = new ArrayList<iSensorChangedHandler>();
	}
	
	public void addSensor(final int sensorType, final int sensorDelayType) {
		Sensor sensor = sensorManager.getDefaultSensor(sensorType);
		sensors.put(sensorType, sensor);
		
		sensorManager.registerListener(this, sensor, sensorDelayType);
	}
	
	public void removeSensor(final int sensorType) {
		Sensor sensor = sensorManager.getDefaultSensor(sensorType);
		sensors.remove(sensorType);
		
		sensorManager.unregisterListener(this, sensor);
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		for (int i = 0; i < accuracyChangedEvents.size(); i++)
			accuracyChangedEvents.get(i).onChange(sensor, accuracy);
		Log.d(TAG, "onAccuracyChanged");
	}

	public void onSensorChanged(SensorEvent event) {
		for (int i = 0; i < sensorChangedEvents.size(); i++)
			sensorChangedEvents.get(i).onChange(event);
		Log.d(TAG, "onAccuracyChanged");
	}

}
