/**
 * 
 * @author Nareshkumar
 * Classname: RequestController
 *
 */
package com.aegon.booking.roombookings.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.booking.roombookings.exception.RoomBookingException;
import com.aegon.booking.roombookings.model.BookingEntity;
import com.aegon.booking.roombookings.service.BookingService;

/**
 * The Class RequestController will check the input restful request and 
 * identify the exact method that matches the restful request.
 */
@RestController
@RequestMapping("/lochside")
public class RequestController {

	/** The BookingService contains the implementation logic for all the operations. */
	@Autowired
	BookingService bookingService;

	/**
	 * This method is used to trigger the implementation method of
	 * new room booking.
	 *
	 * @param bookingEntity : postbody of BookingEntity
	 * @return ResponseEntity : BookingEntity
	 * @throws RoomBookingException if the request validation fails
	 */
	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public ResponseEntity<BookingEntity> newBooking(@RequestBody BookingEntity bookingEntity)
			throws RoomBookingException {
	
		BookingEntity newBooking = new BookingEntity();
		try {
			newBooking = bookingService.newBooking(bookingEntity);
		} catch (RoomBookingException e) {
			throw new RoomBookingException(e.getMessage());
		}
		return ResponseEntity.ok(newBooking);
	}

	/**
	 * This method is used to trigger the implementation method of
	 * retrieving the bookings based on room id.
	 *
	 * @param roomId : Id of Room
	 * @return ResponseEntity : List of BookingEntity
	 */
	@RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<List<BookingEntity>> getBookingByRoomId(@PathVariable("roomId") int roomId) {

		List<BookingEntity> bookingByRoom = new ArrayList<>();
		bookingByRoom = bookingService.getBookingByRoomId(roomId);
		return ResponseEntity.ok(bookingByRoom);
	}

	/**
	 * This method is used to trigger the implementation method of
	 * retrieving the bookings based on customer id.
	 *
	 * @param roomId : Id of Customer
	 * @return ResponseEntity : List of BookingEntity
	 */
	@RequestMapping(value = "/customers/{custId}", method = RequestMethod.GET)
	public ResponseEntity<List<BookingEntity>> getBookingByCustId(@PathVariable("custId") int custId) {
		List<BookingEntity> bookingByCust = new ArrayList<>();
		bookingByCust = bookingService.getBookingByCustId(custId);
		return ResponseEntity.ok(bookingByCust);
	}

	/**
	 * This method is used to trigger the implementation method of
	 * updating the existing booking.
	 *
	 * @param bookingId : Id of the booking
	 * @param bookingEntity : postbody of BookingEntity
	 * @return ResponseEntity : Updated BookingEntity
	 * @throws RoomBookingException if the request validation fails
	 */
	@RequestMapping(value = "/booking/{bookingId}", method = RequestMethod.PUT)
	public ResponseEntity<BookingEntity> updateBooking(@PathVariable("bookingId") Long bookingId,
			@RequestBody BookingEntity bookingEntity) throws RoomBookingException {

		BookingEntity updatedBooking = new BookingEntity();
		try {
			updatedBooking = bookingService.updateBooking(bookingId, bookingEntity);
		} catch (RoomBookingException e) {
			throw new RoomBookingException(e.getMessage());
		}
		return ResponseEntity.ok(updatedBooking);
	}

	/**
	 * This method is used to trigger the implementation method of
	 * getting the availability of specific room at a given range of period.
	 *
	 * @param fromDate : fromdate
	 * @param toDate : todate
	 * @param roomId : Id of the room 
	 * @return ResponseEntity : Map that contains Availability of room
	 */
	@RequestMapping(value = "/availability/from/{fromDate}/to/{toDate}/room/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<Map<LocalDate, String>> getRoomAvailability(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, @PathVariable("roomId") int roomId) {

		LocalDate from = LocalDate.parse(fromDate);
		LocalDate to = LocalDate.parse(toDate);
		Map<LocalDate, String> availableDates = new HashMap<>();
		availableDates = bookingService.getRoomAvailability(from, to, roomId);
		return ResponseEntity.ok(availableDates);
	}
}
