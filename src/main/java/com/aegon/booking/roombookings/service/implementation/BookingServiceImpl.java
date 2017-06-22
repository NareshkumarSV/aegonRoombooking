package com.aegon.booking.roombookings.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegon.booking.roombookings.model.BookingEntity;
import com.aegon.booking.roombookings.repository.BookingEntityRepository;
import com.aegon.booking.roombookings.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingEntityRepository bookingEntityRepository;

	/*public BookingServiceImpl() {

	}*/
	
	@Override
	public BookingEntity newBooking(BookingEntity bookingEntity) {
		
		BookingEntity newBooking =  bookingEntity;
		newBooking.costCalculation(bookingEntity.getRoomType(), 
				LocalDate.parse(bookingEntity.getFromDate()), LocalDate.parse(bookingEntity.getToDate()));
		
		return bookingEntityRepository.save(newBooking);
	}
	
	@Override
	public List<BookingEntity> getBoookingByRoomId(int roomId) {

		List<BookingEntity> allBookings = getAllBookings();
		List<BookingEntity> roomBookings = new ArrayList<>();
		
		if((allBookings != null) && allBookings.size() != 0)
		{
			for(BookingEntity bookingEntity : allBookings)
			{
				if(bookingEntity.getRoomId() == roomId)
				{
					roomBookings.add(bookingEntity);
				}
			}
		}
		return roomBookings;
	}

	@Override
	public List<BookingEntity> getBoookingByCustId(int custId) {

		List<BookingEntity> allBookings = getAllBookings();
		List<BookingEntity> customerBookings = new ArrayList<>();
		
		if((allBookings != null) && allBookings.size() != 0)
		{
			for(BookingEntity bookingEntity : allBookings)
			{
				if(bookingEntity.getCustId() == custId)
				{
					customerBookings.add(bookingEntity);
				}
			}
		}
		return customerBookings;
	}

	@Override
	public BookingEntity updateBooking(Long bookingId, BookingEntity bookingEntity) {
		
		BookingEntity existingBooking = bookingEntityRepository.findOne(bookingId);
		
		if((existingBooking != null) && bookingId.equals(existingBooking.getBookingId()))
		{
			existingBooking = bookingEntity;
			existingBooking.setBookingId(bookingId);
			existingBooking.costCalculation(bookingEntity.getRoomType(), 
					LocalDate.parse(bookingEntity.getFromDate()), LocalDate.parse(bookingEntity.getToDate()));
			
		}
		
		bookingEntityRepository.save(existingBooking);
		
		return existingBooking;
	}
	
	@Override
	public List<BookingEntity> getRoomAvailability() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private List<BookingEntity> getAllBookings() {
		List<BookingEntity> allBookings = bookingEntityRepository.findAll();
		return allBookings;
	}
}
