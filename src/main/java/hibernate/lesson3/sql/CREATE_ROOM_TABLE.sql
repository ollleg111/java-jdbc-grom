
--long id
--int numberOfGuests
--double price
--int breakfastIncluded (1 или 0)
--int petsAllowed (1 или 0)
--Date dateAvailableFrom
--Hotel hotel

CREATE TABLE ROOM(
ROOM_ID NUMBER,
CONSTRAINT ROOM_PK PRIMARY KEY(ROOM_ID),
PRICE NUMBER NOT NULL,
BREAKFAST NUMBER NOT NULL,
PETS NUMBER NOT NULL,
DATE ,
HOTEL_ID NUMBER,
CONSTRAINT HOTEL_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL(HOTEL_ID)
);

