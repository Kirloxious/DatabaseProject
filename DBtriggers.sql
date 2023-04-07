
CREATE TRIGGER incrementHotelCount
AFTER INSERT ON hotel
FOR EACH ROW
    UPDATE HotelChain SET HotelChain.NumberOfHotels = HotelChain.NumberOfHotels + 1
    WHERE HotelChain.HotelChainID = NEW.HotelChainID;


CREATE TRIGGER incrementRoomCount
AFTER INSERT ON Room
FOR EACH ROW
    UPDATE Hotel SET Hotel.NumberOfRooms = Hotel.NumberOfRooms + 1
    WHERE Hotel.HotelID = NEW.HotelID;

ALTER TABLE hotel drop check hotel_chk_1;
ALTER TABLE hotel MODIFY NumberOfRooms int Check (NumberOfRooms >= 0);


