/*
 * Controller class to identify the class for correct rest mapping 
 * and to redirect it.
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

@RestController
@RequestMapping("/lochside")
public class RequestController {

	@Autowired
	BookingService bookingService;

	/*
	 * This method is used to confirm a new booking
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

	/*
	 * This method is used to retrieve the bookings done for corresponding room id.
	 */
	@RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<List<BookingEntity>> getBoookingByRoomId(@PathVariable("roomId") int roomId) {

		List<BookingEntity> bookingByRoom = new ArrayList<>();
		bookingByRoom = bookingService.getBoookingByRoomId(roomId);
		return ResponseEntity.ok(bookingByRoom);
	}

	/*
	 * This method is used to retrieve the bookings done for corresponding customer id.
	 */
	@RequestMapping(value = "/customers/{custId}", method = RequestMethod.GET)
	public ResponseEntity<List<BookingEntity>> getBoookingByCustId(@PathVariable("custId") int custId) {
		List<BookingEntity> bookingByCust = new ArrayList<>();
		bookingByCust = bookingService.getBoookingByCustId(custId);
		return ResponseEntity.ok(bookingByCust);
	}

	/*
	 * This method is used to update the existing booking.
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

	/*
	 * This method is used to get the availability of corresponding room in
	 * a given range of period.
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
