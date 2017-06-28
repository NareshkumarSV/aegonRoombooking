/**
 * 
 * @author Nareshkumar
 * Classname: BookingEntity
 *
 */
package com.aegon.booking.roombookings.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.aegon.booking.roombookings.util.ApplicationConstants;

/**
 * The Class BookingEntity is the data model class for 
 * persisting the booking details.
 */
@Entity
public class BookingEntity {

	/** The id of booking which is unique. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingId;
	
	/** The id of the room. */
	private int roomId;
	
	/** The type of room. */
	private String roomType;
	
	/** The id of customer who booked the room. */
	private int custId;
	
	/** The name of customer who booked the room. */
	private String custName;
	
	/** The date from which the room is required. */
	private LocalDate fromDate;
	
	/** The date till which the room is required. */
	private LocalDate toDate;
	
	/** The total cost of the booking. */
	private int totalCost;
	
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
	 * Gets the room type.
	 *
	 * @return the room type
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Sets the room type.
	 *
	 * @param roomType : the new room type
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * Gets the cust id.
	 *
	 * @return the cust id
	 */
	public int getCustId() {
		return custId;
	}

	/**
	 * Sets the cust id.
	 *
	 * @param custId : the new cust id
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}

	/**
	 * Gets the cust name.
	 *
	 * @return the cust name
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * Sets the cust name.
	 *
	 * @param custName : the new cust name
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public LocalDate getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate : the new from date
	 */
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public LocalDate getToDate() {
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate : the new to date
	 */
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	/**
	 * Gets the total cost.
	 *
	 * @return the total cost
	 */
	public int getTotalCost() {
		return totalCost;
	}
	
	/**
	 * Sets the total cost.
	 *
	 * @param totalCost : the new total cost
	 */
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * Cost calculation.
	 *
	 * @param roomType : type of room
	 * @param fromDate : date from which room is required
	 * @param toDate : date till which room is required
	 */
	public void costCalculation(String roomType, LocalDate fromDate, LocalDate toDate) {
		int days = Period.between(fromDate, toDate).getDays();
		int rate = 0;
		if(ApplicationConstants.SINGLE.equals(roomType))
		{
			rate = 100;
		}
		else if(ApplicationConstants.DOUBLE.equals(roomType))
		{
			rate = 200;
		}
		setTotalCost(days * rate);
	}
}
