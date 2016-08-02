package com.customer.microservices.exception;

public class CustomerException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerException(String message){
		super(message);
	}

	public CustomerException(Exception exception){
		super(exception);
	}
	
	public CustomerException(String message,Exception exception){
		super(message,exception);
	}
	
}
