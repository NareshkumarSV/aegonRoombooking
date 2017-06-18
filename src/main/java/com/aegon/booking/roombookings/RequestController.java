package com.aegon.booking.roombookings;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.booking.roombookings.model.Booking;
import com.aegon.booking.roombookings.service.BookingService;
import com.aegon.booking.roombookings.service.implementation.BookingServiceImpl;

@RestController
@RequestMapping("/lochside")
public class RequestController {

	BookingService bookingService = new BookingServiceImpl();
	
	@RequestMapping("/rooms/{roomId}")
	public List<Booking> getBoookingByRoomId(@PathVariable("roomId") int roomId)
	{
		return bookingService.getBoookingByRoomId(roomId);
	}
	
	@RequestMapping("/customers/{custId}")
	public List<Booking> getBoookingByCustId(@PathVariable("custId") int custId)
	{
		return bookingService.getBoookingByCustId(custId);
	}
	
	@RequestMapping("/availability/{custId}")
	public List<Booking> getRoomAvailability()
	{
		return null;
	}
	
	@RequestMapping(value = "/booking/", method = RequestMethod.POST)
	public Booking newBooking(@RequestBody Booking booking)
	{
		System.out.println("Inside POST - BOOKING");
		bookingService.newBooking(booking);
		return booking;
	}
	
	@RequestMapping(value = "/booking/{bookingId}", method = RequestMethod.PUT)
	public Booking updateBooking(@PathVariable("bookingId") int bookingId, @RequestBody Booking booking)
	{
		return null;
	}
	
	
}
