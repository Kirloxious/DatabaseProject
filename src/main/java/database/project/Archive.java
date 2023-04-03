package database.project;

public class Archive {
	private Integer archiveID;
	private Integer rentingID;
	private Integer bookingId;
	
	
	public Archive(Integer archiveID, Integer rentingID, Integer bookingId) {
		super();
		this.archiveID = archiveID;
		this.rentingID = rentingID;
		this.bookingId = bookingId;
	}
	
	public Integer getArchiveID() {
		return archiveID;
	}
	public void setArchiveID(Integer archiveID) {
		this.archiveID = archiveID;
	}
	public Integer getRentingID() {
		return rentingID;
	}
	public void setRentingID(Integer rentingID) {
		this.rentingID = rentingID;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	
}
