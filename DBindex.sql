CREATE INDEX idx_room_hotelID
ON Room(HotelID);



CREATE INDEX idx_booking_dates
ON Booking(StartDate, EndDate);
CREATE INDEX idx_renting_dates
ON Renting(StartDate, EndDate);

