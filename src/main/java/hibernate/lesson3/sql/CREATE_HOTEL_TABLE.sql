
--long id
--String name
--String country
--String city
--String street

CREATE TABLE HOTEL(
HOTEL_ID NUMBER,
CONSTRAINT HOTEL_PK PRIMARY KEY(HOTEL_ID),
NAME NVARCHAR2(50) NOT NULL,
COUNTRY NVARCHAR2(50) NOT NULL,
CITY NVARCHAR2(50) NOT NULL,
STREET NVARCHAR2(50) NOT NULL
);