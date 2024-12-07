package com.project.courseRegistration.exceptions;

public class PermissionDenyException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6534689454807910062L;

	public PermissionDenyException(String message) {
        super(message);
    }
}
