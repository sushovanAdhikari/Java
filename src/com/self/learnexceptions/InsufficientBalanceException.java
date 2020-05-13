package com.self.learnexceptions;

@SuppressWarnings("serial")

/*
 * Create a custom error exception handling. The class was create using the eclipse setting inheriting 
 * the Exception class
 */
public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException() {
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
