package database.project;

import java.sql.Date;
import java.util.ArrayList;

public class Customer {
	private Integer ssn;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date registeredDate;
	private ArrayList<String> addressess = new ArrayList<>();
	
	public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		if (middleName == null) return "";
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
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public ArrayList<String> getAddressess() {
		return addressess;
	}
	public void addAddress(String address) {
		addressess.add(address);
	}
}
