package com.aegon.booking.roombookings.model;

public class Room {

	private int roomId;
	private String roomType;
	private String customerName;
	
	public Room() {

	}
	
	
	public Room(int roomId, String roomType, String customerName) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.customerName = customerName;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
