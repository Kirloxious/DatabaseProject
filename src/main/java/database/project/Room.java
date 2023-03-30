package database.project;

public class Room {

	private int roomNumber;
	private int roomHotelID;
	private double roomPrice;
	private int roomCapacity;
	private String roomView;
	private boolean extentable;
	private String roomProblem;
	private String roomAmenetie;
	
	
	public Room() {
		
	}
	
	public Room(int roomNumber, int roomHotelID, double roomPrice, int roomCapacity, String roomView, boolean extentable, String roomProblem, String roomAmenetie) {
		this.roomNumber = roomNumber;
		this.roomHotelID = roomHotelID;
		this.roomPrice = roomPrice;
		this.roomCapacity = roomCapacity;
		this.roomView = roomView;
		this.extentable = extentable;
		this.roomProblem = roomProblem;
		this.roomAmenetie = roomAmenetie;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomHotelID() {
		return roomHotelID;
	}

	public void setRoomHotelID(int roomHotelID) {
		this.roomHotelID = roomHotelID;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public String getRoomView() {
		return roomView;
	}

	public void setRoomView(String roomView) {
		this.roomView = roomView;
	}

	public boolean isExtentable() {
		return extentable;
	}

	public void setExtentable(boolean extentable) {
		this.extentable = extentable;
	}

	public String getRoomProblem() {
		return roomProblem;
	}

	public void setRoomProblem(String roomProblem) {
		this.roomProblem = roomProblem;
	}

	public String getRoomAmenetie() {
		return roomAmenetie;
	}

	public void setRoomAmenetie(String roomAmenetie) {
		this.roomAmenetie = roomAmenetie;
	}
	
	@Override
	public String toString() { //TODO full details
		return String.format("Hotel ID: %d, Room number: %d", roomHotelID, roomNumber);
	}
}
