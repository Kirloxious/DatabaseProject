package database.project;

import java.sql.Date;

public class Renting {
	private Integer rentingID;
	private Date startDate;
	private Integer roomNumber;
	private Integer hotelID;
	private Integer customerSSN;
	private Date endDate;
	private Integer checkedInByEmployeeSSN;
	private Integer bookingID;
	
	
	public Renting(Integer rentingID, Date startDate, Integer roomNumber, Integer hotelID, Integer customerSSN,
			Date endDate, Integer checkedInByEmployeeSSN, Integer bookingID) {
		super();
		this.rentingID = rentingID;
		this.startDate = startDate;
		this.roomNumber = roomNumber;
		this.hotelID = hotelID;
		this.customerSSN = customerSSN;
		this.endDate = endDate;
		this.checkedInByEmployeeSSN = checkedInByEmployeeSSN;
		this.bookingID = bookingID;
	}
	
	public Integer getRentingID() {
		return rentingID;
	}
	public void setRentingID(Integer rentingID) {
		this.rentingID = rentingID;
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
	public Integer getCheckedInByEmployeeSSN() {
		return checkedInByEmployeeSSN;
	}
	public void setCheckedInByEmployeeSSN(Integer checkedInByEmployeeSSN) {
		this.checkedInByEmployeeSSN = checkedInByEmployeeSSN;
	}
	public Integer getBookingID() {
		return bookingID;
	}
	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	@Override
	public String toString() {
		return "Renting [rentingID=" + rentingID + ", startDate=" + startDate + ", roomNumber=" + roomNumber
				+ ", hotelID=" + hotelID + ", customerSSN=" + customerSSN + ", endDate=" + endDate
				+ ", checkedInByEmployeeSSN=" + checkedInByEmployeeSSN + ", bookingID=" + bookingID + "]";
	}

}
