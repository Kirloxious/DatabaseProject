SELECT room.HotelID, room.RoomNumber, room.Price, room.Capacity, room.View, room.Extentable FROM room
WHERE NOT EXISTS(
    SELECT Booking.HotelID, Booking.RoomNumber FROM Booking 
    WHERE room.HotelID = Booking.HotelID AND room.RoomNumber = Booking.RoomNumber
    AND NOT EXISTS(SELECT Archived.BookingID FROM Archived WHERE Booking.BookingID = Archived.BookingID)
    ) AND
    NOT EXISTS(
        SELECT Renting.HotelID, Renting.RoomNumber FROM Renting 
        WHERE room.HotelID = Renting.HotelID AND room.RoomNumber = Renting.RoomNumber
        AND NOT EXISTS(SELECT Archived.RentingID FROM Archived WHERE Renting.RentingID = Archived.RentingID)
    );



