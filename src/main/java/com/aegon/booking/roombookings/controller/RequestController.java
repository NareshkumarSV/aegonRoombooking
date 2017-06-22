package com.aegon.booking.roombookings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.booking.roombookings.model.BookingEntity;
import com.aegon.booking.roombookings.service.BookingService;

@RestController
@RequestMapping("/lochside")
public class RequestController {

	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public BookingEntity newBooking(@RequestBody BookingEntity bookingEntity)
	{
		return bookingService.newBooking(bookingEntity); 
	}
	
	@RequestMapping("/rooms/{roomId}")
	public List<BookingEntity> getBoookingByRoomId(@PathVariable("roomId") int roomId)
	{
		return bookingService.getBoookingByRoomId(roomId);
		
	}
	
	@RequestMapping("/customers/{custId}")
	public List<BookingEntity> getBoookingByCustId(@PathVariable("custId") int custId)
	{
		return bookingService.getBoookingByCustId(custId);
	}
	
	@RequestMapping(value = "/booking/{bookingId}", method = RequestMethod.PUT)
	public BookingEntity updateBooking(@PathVariable("bookingId") Long bookingId, @RequestBody BookingEntity bookingEntity)
	{
		return bookingService.updateBooking(bookingId, bookingEntity);
	}

	@RequestMapping("/availability/from/{fromDate}/to/{toDate}")
	public List<BookingEntity> getRoomAvailability(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") int toDate)
	{
		return null;
	}
}
