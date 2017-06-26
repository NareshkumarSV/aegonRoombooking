package com.aegon.booking.roombookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class RoomBookingException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public RoomBookingException(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String toString()
	{
		return this.errorMessage;
	}

}
