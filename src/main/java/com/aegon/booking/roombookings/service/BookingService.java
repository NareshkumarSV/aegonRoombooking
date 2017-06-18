package com.aegon.booking.roombookings.service;

import java.util.List;

import com.aegon.booking.roombookings.model.Booking;

public interface BookingService {

	public abstract List<Booking> getBoookingByRoomId(int roomId);
	
	public abstract List<Booking> getBoookingByCustId(int custId);
	
	public abstract List<Booking> getRoomAvailability();
	
	public abstract Booking newBooking(Booking booking);
	
	public abstract Booking updateBooking(int bookingId, Booking booking);
}
