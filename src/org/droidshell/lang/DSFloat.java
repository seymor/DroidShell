package org.droidshell.lang;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class DSFloat {
	
	public float value;
	
	public DSFloat() {
		value = -1.0f;
	}
	
	public DSFloat(float value) {
		this.value = value;
	}
	
	public Float toFloat() {
		return Float.valueOf(value);
	}

}
