/**
 * 
 * @author Nareshkumar
 * Classname: RoomEntity
 *
 */
package com.aegon.booking.roombookings.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class RoomEntity is the data model class for 
 * persisting the room details.
 */
@Entity
public class RoomEntity {

	/** The availability id which is unique. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long availabilityId;
	
	/** The id of the booking. */
	private Long bookingId;
	
	/** The id of the room. */
	private int roomId;
	
	/** The available day. */
	private LocalDate availableDay;
	
	/**
	 * Gets the availability id.
	 *
	 * @return the availability id
	 */
	public Long getAvailabilityId() {
		return availabilityId;
	}
	
	/**
	 * Sets the availability id.
	 *
	 * @param availabilityId : the new availability id
	 */
	public void setAvailabilityId(Long availabilityId) {
		this.availabilityId = availabilityId;
	}
	
	/**
	 * Gets the booking id.
	 *
	 * @return the booking id
	 */
	public Long getBookingId() {
		return bookingId;
	}
	
	/**
	 * Sets the booking id.
	 *
	 * @param bookingId : the new booking id
	 */
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	
	/**
	 * Gets the room id.
	 *
	 * @return the room id
	 */
	public int getRoomId() {
		return roomId;
	}
	
	/**
	 * Sets the room id.
	 *
	 * @param roomId : the new room id
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	/**
	 * Gets the available day.
	 *
	 * @return the available day
	 */
	public LocalDate getAvailableDay() {
		return availableDay;
	}
	
	/**
	 * Sets the available day.
	 *
	 * @param availableDay : the new available day
	 */
	public void setAvailableDay(LocalDate availableDay) {
		this.availableDay = availableDay;
	}

}
