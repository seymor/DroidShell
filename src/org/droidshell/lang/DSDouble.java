package org.droidshell.lang;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class DSDouble {

	public double value;
	
	public DSDouble() {
		value = -1.0;
	}
	
	public DSDouble(double value) {
		this.value = value;
	}
	
	public Double toDouble() {
		return new Double(value);
	}
	
}
