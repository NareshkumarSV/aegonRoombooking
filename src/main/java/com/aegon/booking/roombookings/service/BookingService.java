/**
 * 
 * @author Nareshkumar
 * Classname: BookingService
 *
 */
package com.aegon.booking.roombookings.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.aegon.booking.roombookings.exception.RoomBookingException;
import com.aegon.booking.roombookings.model.BookingEntity;

/**
 * The Interface BookingService which contains all the abstract methods
 * to handle room booking.
 */
public interface BookingService {

	/**
	 * This abstract method would be implemented for retrieving the 
	 * bookings based on the room id.
	 *
	 * @param roomId : the room id
	 * @return List<BookingEntity>: the list of booking entity
	 */
	public abstract List<BookingEntity> getBookingByRoomId(int roomId);

	/**
	 * This abstract method would be implemented for retrieving the 
	 * bookings based on the customer id.
	 *
	 * @param custId : the customer id
	 * @return List<BookingEntity>: the list of booking entity
	 */
	public abstract List<BookingEntity> getBookingByCustId(int custId);

	/**
	 * This abstract method would be implemented for retrieving the 
	 * availability of specific room within the provided range of period.
	 *
	 * @param fromDate : fromdate
	 * @param toDate : todate
	 * @param roomId : Id of room
	 * @return Map : availability of room
	 */
	public abstract Map<LocalDate, String> getRoomAvailability(LocalDate fromDate, LocalDate toDate, int roomId);

	/**
	 * This abstract method would be implemented for creating a new booking.
	 *
	 * @param bookingEntity : post body of BookingEntity
	 * @return BookingEntity: the new BookingEntity
	 * @throws RoomBookingException : If request validation fails
	 */
	public abstract BookingEntity newBooking(BookingEntity bookingEntity) throws RoomBookingException;

	/**
	 * This abstract method would be implemented for updating the existing 
	 * booking.
	 *
	 * @param bookingId : the booking to be updated
	 * @param bookingEntity : post body of BookingEntity
	 * @return BookingEntity: the updated BookingEntity
	 * @throws RoomBookingException : If request validation fails
	 */
	public abstract BookingEntity updateBooking(Long bookingId, BookingEntity bookingEntity)
			throws RoomBookingException;

}
