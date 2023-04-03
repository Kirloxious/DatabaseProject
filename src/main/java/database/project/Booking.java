package database.project;

import java.sql.Date;

public class Booking {
	private Integer bookingID;
	private Date startDate;
	private Integer roomNumber;
	private Integer hotelID;
	private Integer customerSSN;
	private Date endDate;
	
	
	public Booking(Integer bookingID, Date startDate, Integer roomNumber, Integer hotelID, Integer customerSSN,
			Date endDate) {
		super();
		this.bookingID = bookingID;
		this.startDate = startDate;
		this.roomNumber = roomNumber;
		this.hotelID = hotelID;
		this.customerSSN = customerSSN;
		this.endDate = endDate;
	}
	
	public Integer getBookingID() {
		return bookingID;
	}
	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getHotelID() {
		return hotelID;
	}
	public void setHotelID(Integer hotelID) {
		this.hotelID = hotelID;
	}
	public Integer getCustomerSSN() {
		return customerSSN;
	}
	public void setCustomerSSN(Integer customerSSN) {
		this.customerSSN = customerSSN;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", startDate=" + startDate + ", roomNumber=" + roomNumber
				+ ", hotelID=" + hotelID + ", customerSSN=" + customerSSN + ", endDate=" + endDate + "]";
	}
	
}
