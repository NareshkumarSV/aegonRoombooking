package com.aegon.booking.roombookings.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.aegon.booking.roombookings.exception.RoomBookingException;
import com.aegon.booking.roombookings.model.BookingEntity;

public interface BookingService {

	public abstract List<BookingEntity> getBoookingByRoomId(int roomId);

	public abstract List<BookingEntity> getBoookingByCustId(int custId);

	public abstract Map<LocalDate, String> getRoomAvailability(LocalDate fromDate, LocalDate toDate, int roomId);

	public abstract BookingEntity newBooking(BookingEntity bookingEntity) throws RoomBookingException;

	public abstract BookingEntity updateBooking(Long bookingId, BookingEntity bookingEntity)
			throws RoomBookingException;

}
