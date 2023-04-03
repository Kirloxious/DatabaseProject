
CREATE VIEW HotelRoomCapacities AS 
SELECT HotelID, RoomNumber, Capacity
FROM Room;


CREATE VIEW AvailableRoomsPerArea AS
SELECT Hotel.Address AS Area, Hotel.HotelChainID, Hotel.HotelID, Hotel.StarRating, Count(Room.HotelID) AS NumberOfRooms FROM Hotel, room
WHERE Hotel.HotelID = Room.HotelID 
AND NOT EXISTS(
    SELECT Booking.HotelID, Booking.RoomNumber FROM Booking 
    WHERE room.HotelID = Booking.HotelID AND room.RoomNumber = Booking.RoomNumber
    AND NOT EXISTS(SELECT Archived.BookingID FROM Archived WHERE Booking.BookingID = Archived.BookingID)
    ) AND
    NOT EXISTS(
        SELECT Renting.HotelID, Renting.RoomNumber FROM Renting 
        WHERE room.HotelID = Renting.HotelID AND room.RoomNumber = Renting.RoomNumber
        AND NOT EXISTS(SELECT Archived.RentingID FROM Archived WHERE Renting.RentingID = Archived.RentingID)
    )
GROUP BY Hotel.Address, Hotel.HotelChainID, Hotel.HotelID, Hotel.StarRating;