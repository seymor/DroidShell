package org.droidshell.exception;

/**
 * (c) 2012 Zsolt Vad
 * 
 * @author Zsolt Vad
 * @since 00:00:00 - 01.03.2012
 */
public abstract class DroidShellException extends RuntimeException{

	private static final long serialVersionUID = -5871826522303389291L;
	
	public DroidShellException(String message) {
		super(message);
	}

}
