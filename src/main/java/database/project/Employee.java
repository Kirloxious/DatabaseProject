package database.project;

import java.util.ArrayList;

public class Employee {
	private Integer ssn;
	private Integer hotelID;
	private String firstName;
	private String middleName;
	private String lastName;
	private ArrayList<String> addressess = new ArrayList<>();
	private ArrayList<String> roles = new ArrayList<>();
	public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
	public Integer getHotelID() {
		return hotelID;
	}
	public void setHotelID(Integer hotelID) {
		this.hotelID = hotelID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ArrayList<String> getAddressess() {
		return addressess;
	}
	public void addAddress(String address) {
		this.addressess.add(address);
	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void addRole(String role) {
		this.roles.add(role);
	}
}
