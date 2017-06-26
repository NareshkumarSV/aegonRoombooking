package com.aegon.booking.roombookings.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long availabilityId;
	
	private Long bookingId;
	private int roomId;
	private LocalDate availableDay;
	
	public Long getAvailabilityId() {
		return availabilityId;
	}
	public void setAvailabilityId(Long availabilityId) {
		this.availabilityId = availabilityId;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public LocalDate getAvailableDay() {
		return availableDay;
	}
	public void setAvailableDay(LocalDate availableDay) {
		this.availableDay = availableDay;
	}

}
