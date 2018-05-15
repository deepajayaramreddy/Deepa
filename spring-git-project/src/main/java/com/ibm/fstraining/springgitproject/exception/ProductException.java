/**
 * 
 */
package com.ibm.fstraining.springgitproject.exception;

public class ProductException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = null;

	public ProductException() {
		super();
	}

	public ProductException(String message) {
		super(message);
		this.message = message;
	}

	public ProductException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}
}