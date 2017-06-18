package com.aegon.booking.roombookings.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aegon.booking.roombookings.model.Booking;
import com.aegon.booking.roombookings.service.BookingService;

public class BookingServiceImpl implements BookingService{

	private static List<Booking> alBooking;
	
	static{
		alBooking = getAllBookings();	
	}
	
	
	@Override
	public List<Booking> getBoookingByRoomId(int roomId) {
	
		System.out.println("Inside BookingServiceImpl.getBoookingByRoomId() " + roomId);
		
		List<Booking> alRoomBooking = new ArrayList<>();
		
		for(Booking booking : alBooking)
		{
			if(booking.getRoomId() == roomId)
			{
				alRoomBooking.add(booking);
			}
		}
			
		return alRoomBooking;
	}


	@Override
	public List<Booking> getBoookingByCustId(int custId) {
		
		List<Booking> alCustBooking = new ArrayList<>();
		
		for(Booking booking : alBooking)
		{
			if(booking.getCustId() == custId)
			{
				alCustBooking.add(booking);
			}
		}
			
		return alCustBooking;
	}

	@Override
	public List<Booking> getRoomAvailability() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking newBooking(Booking booking) {
		booking.setBookingId(alBooking.size() + 1);
		
		/*Booking newBook = new Booking(booking.getBookingId(), booking.getRoomId(), booking.getRoomType(),
				booking.getCustId(), booking.getCustName(), booking.getFromDate(), booking.getToDate());*/
		alBooking.add(booking);
		return booking;
	}

	@Override
	public Booking updateBooking(int bookingId, Booking booking) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Booking> getAllBookings() {
		Booking book1 = new Booking(1, 1, "single", 1, "vimal", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book2 = new Booking(2, 2, "single", 2, "vinoth", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book3 = new Booking(3, 3, "double", 1, "vimal", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book4 = new Booking(4, 4, "double", 2, "vinoth", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book5 = new Booking(5, 5, "double", 2, "vinoth", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book6 = new Booking(6, 1, "single", 3, "ramesh", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book7 = new Booking(7, 2, "single", 4, "dinesh", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book8 = new Booking(8, 3, "double", 5, "suresh", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		
		List<Booking> alBookings = new ArrayList<>();
		
		alBookings.add(book1);
		alBookings.add(book2);
		alBookings.add(book3);
		alBookings.add(book4);
		alBookings.add(book5);
		alBookings.add(book6);
		alBookings.add(book7);
		alBookings.add(book8);
		
		return alBookings;
	}
	
}
