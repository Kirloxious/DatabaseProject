package database.project;

import java.util.ArrayList;

public class Hotel {
	private Integer hotelChainID;
	private Integer numberOfRooms;
	private Integer starRating;
	private String address;
	private String email;
	private Integer hotelID;
	private ArrayList<String> phoneNumbers = new ArrayList<>();
	
	public Integer getHotelChainID() {
		return hotelChainID;
	}
	public void setHotelChainID(Integer hotelChainID) {
		this.hotelChainID = hotelChainID;
	}
	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public Integer getStarRating() {
		return starRating;
	}
	public void setStarRating(Integer starRating) {
		this.starRating = starRating;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getHotelID() {
		return hotelID;
	}
	public void setHotelID(Integer hotelID) {
		this.hotelID = hotelID;
	}
	public ArrayList<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void addPhoneNumber(String phoneNumber) {
		phoneNumbers.add(phoneNumber);
	}
}
