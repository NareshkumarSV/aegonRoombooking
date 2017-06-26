package com.aegon.booking.roombookings.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.aegon.booking.roombookings.util.ApplicationConstants;

@Entity
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingId;
	
	private int roomId;
	private String roomType;
	private int custId;
	private String custName;
	private LocalDate fromDate;
	private LocalDate toDate;
	
	private int totalCost;
	
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public int getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

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
