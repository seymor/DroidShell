package org.droidshell.exception;

public abstract class DroidShellException extends RuntimeException{

	private static final long serialVersionUID = -5871826522303389291L;
	
	public DroidShellException(String message) {
		super(message);
	}

}
