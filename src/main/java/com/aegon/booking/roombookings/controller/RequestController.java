package com.aegon.booking.roombookings.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aegon.booking.roombookings.model.Booking;
import com.aegon.booking.roombookings.model.BookingEntity;
import com.aegon.booking.roombookings.repository.BookingEntityRepository;
import com.aegon.booking.roombookings.service.BookingService;
import com.aegon.booking.roombookings.service.implementation.BookingServiceImpl;
import com.aegon.booking.roombookings.service.implementation.BookingServiceImplOLD;

@RestController
@RequestMapping("/lochside")
public class RequestController {

	/*@Autowired
	BookingService bookingService = new BookingServiceImpl();*/
	
	@Autowired
	BookingService bookingService;
	
	/*@Autowired
	BookingEntityRepository bookingEntityRepository;*/
	
	/*@RequestMapping("/rooms/{roomId}")
	public List<Booking> getBoookingByRoomId(@PathVariable("roomId") int roomId)
	{
		return bookingService.getBoookingByRoomId(roomId);
	}*/
	
	@RequestMapping(value = "/booking/", method = RequestMethod.POST)
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
	
	@RequestMapping("/availability/from/{fromDate}/to/{toDate}")
	public List<Booking> getRoomAvailability(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") int toDate)
	{
		return null;
	}
	
	@RequestMapping(value = "/booking/{bookingId}", method = RequestMethod.PUT)
	public BookingEntity updateBooking(@PathVariable("bookingId") Long bookingId, @RequestBody BookingEntity bookingEntity)
	{
		return bookingService.updateBooking(bookingId, bookingEntity);
	}
	
	/*@RequestMapping(value = "/booking/", method = RequestMethod.POST)
	public Booking newBooking(@RequestBody Booking booking)
	{
		
		bookingService.newBooking(booking);
		return booking;
	}*/
	
	
	/*@RequestMapping(value = "/booking/{bookingId}", method = RequestMethod.PUT)
	public Booking updateBooking(@PathVariable("bookingId") Long bookingId, @RequestBody Booking booking)
	{
		Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
		return updatedBooking;
		return null;
	}*/
	
	
}
