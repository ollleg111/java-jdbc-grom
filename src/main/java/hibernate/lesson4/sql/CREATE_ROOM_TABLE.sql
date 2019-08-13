
--    private long id;
--    private int numberOgGuests;
--    private double price;
--    private int breakfastIncluded;
--    private int petsAllowed;
--    private Date dateAvailableFrom;
--    private Hotel hotel;

CREATE TABLE ROOM(
ROOM_ID NUMBER,
CONSTRAINT ROOM_PK PRIMARY KEY(ROOM_ID),
NUMBER_GUESTS NUMBER(10) NOT NULL,
PRICE NUMBER (10,2) NOT NULL,
BREAKFAST NUMBER(1) NOT NULL,
CHECK (BREAKFAST BETWEEN 0 AND 1),
PETS NUMBER(1) NOT NULL,
CHECK (PETS BETWEEN 0 AND 1),
DATE_AVAILABLE TIMESTAMP,
HOTEL_ID NUMBER NOT NULL,
CONSTRAINT HOTEL_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL(HOTEL_ID)
);

