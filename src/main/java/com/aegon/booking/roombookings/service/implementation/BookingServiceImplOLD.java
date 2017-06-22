package com.aegon.booking.roombookings.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.aegon.booking.roombookings.model.Booking;
import com.aegon.booking.roombookings.service.BookingService;

public class BookingServiceImplOLD{ //implements BookingService{

	private static List<Booking> alBooking;
	
	static{
		alBooking = getAllBookings();	
	}
	
	
	//@Override
	public List<Booking> getBoookingByRoomId(int roomId) {
	
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


	//@Override
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

	//@Override
	public List<Booking> getRoomAvailability() {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public Booking newBooking(Booking booking) {
		booking.setBookingId(alBooking.size() + 1);
		booking.setTotalCost(booking.getRoomType(), LocalDate.parse(booking.getFromDate()), LocalDate.parse(booking.getToDate()));
		alBooking.add(booking);
		return booking;
	}

	//@Override
	public Booking updateBooking(int bookingId, Booking booking) {

		Booking updatedBooking = new Booking();
		
		for(Booking findBooking : alBooking)
		{
			if(findBooking.getBookingId() == bookingId)
			{
				updatedBooking.setBookingId(bookingId);
				updatedBooking.setCustId((booking.getCustId() != 0) ? booking.getCustId() : findBooking.getCustId());
				updatedBooking.setCustName((booking.getCustName().equals(null)) ? findBooking.getCustName() : booking.getCustName());
				updatedBooking.setFromDate((booking.getFromDate().equals(null)) ? findBooking.getFromDate() : booking.getFromDate());
				updatedBooking.setRoomId((booking.getRoomId() != 0) ? booking.getRoomId() : findBooking.getRoomId());
				updatedBooking.setRoomType((booking.getRoomType().equals(null)) ? findBooking.getRoomType() : booking.getRoomType());
				updatedBooking.setToDate((booking.getToDate().equals(null)) ? findBooking.getToDate() : booking.getToDate());
				updatedBooking.setTotalCost(updatedBooking.getRoomType(), LocalDate.parse(updatedBooking.getFromDate()), LocalDate.parse(updatedBooking.getToDate()));
				
				//updatedBooking = findBooking;
				break;
			}
		}
		return updatedBooking;
	}

	private static List<Booking> getAllBookings() {
		Booking book1 = new Booking(1, 1, "single", 1, "vimal", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book2 = new Booking(2, 2, "single", 2, "vinoth", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book3 = new Booking(3, 3, "double", 1, "vimal", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book4 = new Booking(4, 4, "double", 2, "vinoth", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book5 = new Booking(5, 5, "double", 2, "vinoth", LocalDate.of(2017, 7, 20), LocalDate.of(2017, 7, 25));
		Booking book6 = new Booking(6, 1, "single", 3, "ramesh", LocalDate.of(2017, 7, 28), LocalDate.of(2017, 7, 29));
		Booking book7 = new Booking(7, 2, "single", 4, "dinesh", LocalDate.of(2017, 7, 28), LocalDate.of(2017, 7, 29));
		Booking book8 = new Booking(8, 3, "double", 5, "suresh", LocalDate.of(2017, 7, 28), LocalDate.of(2017, 7, 29));
		
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
