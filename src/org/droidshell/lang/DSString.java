package org.droidshell.lang;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class DSString {
	
	public String value;
	
	public DSString() {
		value = "";
	}
	
	public DSString(String value) {
		this.value = value;
	}
	
	public String toString() {
		return new String(value);
	}

}
