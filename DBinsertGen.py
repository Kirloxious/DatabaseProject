import random
import string
from faker import Faker
fake = Faker()


#fake.address()
#random address of form:
# '426 Jordy Lodge
#  Cartwrightshire, SC 88120-6700'

#HotelChain
HotelChainInsert = "INSERT INTO HotelChain(NumberOfHotels, HotelChainID, CentralOfficeAddress)"

#Hotel
HotelInsert = "INSERT INTO Hotel(HotelChainID, NumberOfRooms, StarRating, Address, ContactEmail, HotelID)"


#Room
RoomInsert = "INSERT INTO Room(HotelID, RoomNumber, Price, Capacity, View, Extentable)" 


def randomChar(char_num):
       return ''.join(random.choice(string.ascii_letters) for _ in range(char_num))

def randomEmail():
    return randomChar(random.randrange(10, 20))+"@gmail.com"

def genPhone():
    first = str(random.randint(100,999))
    second = str(random.randint(1,888)).zfill(3)

    last = (str(random.randint(1,9998)).zfill(4))
    while last in ['1111','2222','3333','4444','5555','6666','7777','8888']:
        last = (str(random.randint(1,9998)).zfill(4))
        
    return '{}-{}-{}'.format(first,second, last)

def genHotelChain(ID):
    return HotelChainInsert+" VALUES ({}, {}, '{}');".format(8, ID, fake.address())

def genHotel(HotelChainID, ID):
    return HotelInsert+" VALUES ({}, {}, {}, '{}', '{}', {});".format(HotelChainID, 5, random.randrange(1, 6), fake.address(), randomEmail(), ID)

def genRoom(HotelID, roomNbr):
    return RoomInsert+" VALUES ({}, {}, {}, {}, '{}', {});".format(HotelID, roomNbr, round(random.uniform(200,1500), 2), random.randrange(6), random.choice(['Mountain', 'Sea']), bool(random.getrandbits(1)))


f = open("dbInserts.sql", "x")

for i in range(1,6):
    HCid = 100+i
    f.write(genHotelChain(HCid)+"\n")
    for j in range(1, 9):
        Hid = 1000+j
        f.write("\t"+genHotel(HCid, Hid)+"\n")
        for k in range(1, 6):
            f.write("\t\t"+genRoom(Hid, k)+"\n")
            
