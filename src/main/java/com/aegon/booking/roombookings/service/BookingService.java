package com.aegon.booking.roombookings.service;

import java.util.List;

import com.aegon.booking.roombookings.model.BookingEntity;

public interface BookingService {

	public abstract List<BookingEntity> getBoookingByRoomId(int roomId);
	
	public abstract List<BookingEntity> getBoookingByCustId(int custId);
	
	public abstract List<BookingEntity> getRoomAvailability();
	
	public abstract BookingEntity newBooking(BookingEntity bookingEntity);
	
	public abstract BookingEntity updateBooking(Long bookingId, BookingEntity bookingEntity);

}
