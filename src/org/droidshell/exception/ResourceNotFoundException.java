package org.droidshell.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3306654541567045416L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
