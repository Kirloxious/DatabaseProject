package database.project;

public class RoomView {
	private String area;
	private int hotelChainId;
	private int hotelId;
	private int starRating;
	private int numOfRooms;
	private int roomNumber;
	private int capacity;
	
	
	public RoomView(int hotelId, int roomNumber, int capacity) {
		super();
		this.hotelId = hotelId;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
	}
	public RoomView(String area, int hotelChainId, int hotelId, int starRating, int numOfRooms) {
		super();
		this.area = area;
		this.hotelChainId = hotelChainId;
		this.hotelId = hotelId;
		this.starRating = starRating;
		this.numOfRooms = numOfRooms;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getHotelChainId() {
		return hotelChainId;
	}
	public void setHotelChainId(int hotelChainId) {
		this.hotelChainId = hotelChainId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getStarRating() {
		return starRating;
	}
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	public int getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
