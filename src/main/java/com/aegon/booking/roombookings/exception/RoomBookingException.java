/**
 * 
 * @author Nareshkumar
 * Classname: RoomBookingException
 *
 */
package com.aegon.booking.roombookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class RoomBookingException is used to handle application exception.
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class RoomBookingException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The error message to be defined for each case. */
	private String errorMessage;
	
	/**
	 * Instantiates a new room booking exception.
	 *
	 * @param errorMessage : the error message
	 */
	public RoomBookingException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString()
	{
		return this.errorMessage;
	}

}
