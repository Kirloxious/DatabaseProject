  
DELIMITER @@
CREATE TRIGGER hasManagerInsert
BEFORE INSERT ON hotel
FOR EACH ROW
BEGIN
   IF Not Exists(Select Employee, RoleTitle From Employee, Role where Employee.HotelID = Hotel.HotelID AND RoleTitle like "Manager") THEN
        signal sqlstate '45000' set message_text = 'Manager must exist';       
   END IF;
END @@ 
DELIMITER ;

DELIMITER @@
CREATE TRIGGER hasManagerUpdate
BEFORE UPDATE ON hotel
FOR EACH ROW
BEGIN
   IF Not Exists(Select Employee, RoleTitle From Employee, Role where Employee.HotelID = Hotel.HotelID AND RoleTitle like "Manager") THEN
        signal sqlstate '45000' set message_text = 'Manager must exist';       
   END IF;
END @@ 
DELIMITER ;


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


