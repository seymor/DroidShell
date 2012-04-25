package org.droidshell.lang;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class DSInteger {
	
	public int value;
	
	public DSInteger() {
		value = -1;
	}
	
	public DSInteger(int value) {
		this.value = value;
	}
	
	public Integer toInteger() {
		return Integer.valueOf(value);
	}

}
