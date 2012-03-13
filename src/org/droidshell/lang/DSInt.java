package org.droidshell.lang;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class DSInt {
	
	public int value;
	
	public DSInt() {
		value = -1;
	}
	
	public DSInt(int value) {
		this.value = value;
	}
	
	public Integer toInteger() {
		return new Integer(value);
	}

}
