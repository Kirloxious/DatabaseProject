package database.project;

import java.util.ArrayList;

public class Room {
	private int roomNumber;
	private int roomHotelID;
	private double roomPrice;
	private int roomCapacity;
	private String roomView;
	private boolean extentable;
	private ArrayList<String> roomProblems = new ArrayList<>();
	private ArrayList<String> roomAmeneties = new ArrayList<>();
	
	
	public Room() {
		
	}
	
	public Room(int roomNumber, int roomHotelID, double roomPrice, int roomCapacity, String roomView, boolean extentable) {
		this.roomNumber = roomNumber;
		this.roomHotelID = roomHotelID;
		this.roomPrice = roomPrice;
		this.roomCapacity = roomCapacity;
		this.roomView = roomView;
		this.extentable = extentable;
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

	
	public ArrayList<String> getRoomProblems() {
		return roomProblems;
	}

	public ArrayList<String> getRoomAmeneties() {
		return roomAmeneties;
	}
	public void addProblem(String problem) {
		roomProblems.add(problem);
	}
	public void addAmenetie(String amenetie) {
		roomAmeneties.add(amenetie);
	}

	@Override
	public String toString() {
		String ameneties = String.join(", ", roomAmeneties);
		String problems = String.join(", ", roomProblems);
		return String.format("Hotel ID: %d, Room number: %d, Price: %f, Capacity: %d, View %s, Extendable: %b, Ameneties:(%s), Problems:(%s)", 
				roomHotelID, roomNumber, roomPrice, roomCapacity, roomView, extentable, ameneties, problems);
	}
}
