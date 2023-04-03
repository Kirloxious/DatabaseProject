

CREATE TABLE IF NOT EXISTS HotelChain(
NumberOfHotels int Check (NumberOfHotels >= 0),
HotelChainID int Not Null Auto_Increment, 
CentralOfficeAddress varchar(200),
Primary Key (HotelChainId) 
);
CREATE TABLE IF NOT EXISTS HotelChainPhoneNumber(
HotelChainID int Not Null,
PhoneNumber VarChar(50) Not Null,
Foreign Key (HotelChainID) References HotelChain(HotelChainID) ON DELETE CASCADE,
Primary Key (HotelChainID, PhoneNumber)
);
CREATE TABLE IF NOT EXISTS HotelChainEmail(
HotelChainID int Not Null,
Email VarChar(50) Not Null,
Foreign Key (HotelChainID) References HotelChain(HotelChainID) ON DELETE CASCADE,
Primary Key (HotelChainID, Email)
);

CREATE TABLE IF NOT EXISTS Hotel(
HotelChainID int Not Null, 
NumberOfRooms int Check (NumberOfRooms >= 0),
StarRating int(1) Check (StarRating > 0 AND StarRating < 6),
Address varchar(200),
ContactEmail varchar(50),
HotelID int Not Null Auto_Increment,
Primary Key (HotelID),
Foreign Key (HotelChainID) References HotelChain(HotelChainID) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS HotelPhoneNumber(
HotelID int Not Null,
PhoneNumber VarChar(50) Not Null,
Foreign Key (HotelID) References Hotel(HotelID) ON DELETE CASCADE,
Primary Key (HotelID, PhoneNumber)
);

CREATE TABLE IF NOT EXISTS Room(
HotelID int Not Null,
RoomNumber int Not Null,
Price Decimal(10,2) Check (Price > 0),
Capacity int Check (Capacity > 0),
View Varchar(50) Check (View in ("Mountain", "Sea")),
Extentable bool Not Null,
Foreign Key (HotelID) References Hotel(HotelID) ON DELETE CASCADE,
Primary Key (HotelID, RoomNumber)
);
CREATE TABLE IF NOT EXISTS RoomProblems(
HotelID int Not Null,
RoomNumber int Not Null,
Problem VarChar(50) Not Null,
Foreign Key (HotelID, RoomNumber) References Room(HotelID, RoomNumber) ON DELETE CASCADE,
Primary Key (HotelID, RoomNumber, Problem)
);
CREATE TABLE IF NOT EXISTS RoomAmenities(
HotelID int Not Null,
RoomNumber int Not Null,
Amenetie VarChar(50) Not Null,
Foreign Key (HotelID, RoomNumber) References Room(HotelID, RoomNumber) ON DELETE CASCADE,
Primary Key (HotelID, RoomNumber, Amenetie)
);
CREATE TABLE IF NOT EXISTS Employee (
  SSN int NOT NULL,
  HotelID int NOT NULL,
  FirstName varchar(50) NOT NULL,
  MiddleName varchar(50),
  LastName varchar(50) NOT NULL,
  PRIMARY KEY (SSN),
  FOREIGN KEY (HotelID) REFERENCES Hotel(HotelID)
);

CREATE TABLE IF NOT EXISTS Role(
  SSN int NOT NULL,
  RoleTitle varchar(50) NOT NULL,
  PRIMARY KEY (SSN, RoleTitle),
  FOREIGN KEY (SSN) REFERENCES Employee(SSN)
);

CREATE TABLE IF NOT EXISTS EmployeeAddress (
  SSN int NOT NULL,
  Address varchar(50) NOT NULL,
  PRIMARY KEY (SSN, Address),
  FOREIGN KEY (SSN) REFERENCES Employee(SSN)
);

CREATE TABLE IF NOT EXISTS Customer (
  SSN int NOT NULL,
  FirstName varchar(50) NOT NULL,
  MiddleName varchar(50),
  LastName varchar(50) NOT NULL,
  RegisterDate date NOT NULL,
  PRIMARY KEY (SSN)
);

CREATE TABLE IF NOT EXISTS CustomerAddress (
  SSN int NOT NULL,
  Address varchar(50) NOT NULL,
  PRIMARY KEY (SSN, Address),
  FOREIGN KEY (SSN) REFERENCES Customer(SSN)
);

CREATE TABLE IF NOT EXISTS Booking (
  BookingID int NOT NULL AUTO_INCREMENT,
  StartDate date NOT NULL,
  RoomNumber int,
  HotelID int,
  Customer int NOT NULL,
  EndDate date NOT NULL,
  PRIMARY KEY (BookingID),
  FOREIGN KEY (HotelID, RoomNumber) REFERENCES Room(HotelID, RoomNumber) ON DELETE SET NULL,
  FOREIGN KEY (Customer) REFERENCES Customer(SSN),
  CHECK (StartDate < EndDate) -- must be one night
);

CREATE TABLE IF NOT EXISTS Renting (
  RentingID int NOT NULL AUTO_INCREMENT,
  StartDate date NOT NULL,
  RoomNumber int,
  HotelID int,
  Customer int NOT NULL,
  EndDate date NOT NULL,
  CheckedInByEmployeeID int NOT NULL,
  BookingID int,
  PRIMARY KEY (RentingID),
  FOREIGN KEY (HotelID, RoomNumber) REFERENCES Room(HotelID, RoomNumber) ON DELETE SET NULL,
  FOREIGN KEY (Customer) REFERENCES Customer(SSN),
  FOREIGN KEY (CheckedInByEmployeeID) REFERENCES Employee(SSN),
  FOREIGN KEY (BookingID) REFERENCES Booking(BookingID),
  CHECK (StartDate < EndDate) -- must be one night
);

CREATE TABLE IF NOT EXISTS Archived (
  ArchivedID int NOT NULL AUTO_INCREMENT,
  BookingID int,
  RentingID int,
  PRIMARY KEY (ArchivedID),
  FOREIGN KEY (BookingID) REFERENCES Booking(BookingID),
  FOREIGN KEY (RentingID) REFERENCES Renting(RentingID)
);

 

 

