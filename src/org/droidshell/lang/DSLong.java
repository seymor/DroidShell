package org.droidshell.lang;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public class DSLong {
	
	public long value;
	
	public DSLong() {
		value = -1L;
	}
	
	public DSLong(long value) {
		this.value = value;
	}
	
	public Long toLong() {
		return Long.valueOf(value);
	}

}
