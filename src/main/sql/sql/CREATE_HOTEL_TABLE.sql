
--    private long id;
--    private String name;
--    private String country;
--    private String city;
--    private String street;
--    private List rooms;

CREATE TABLE HOTEL(
HOTEL_ID NUMBER,
CONSTRAINT HOTEL_PK PRIMARY KEY(HOTEL_ID),
NAME NVARCHAR2(50) NOT NULL,
COUNTRY NVARCHAR2(50) NOT NULL,
CITY NVARCHAR2(50) NOT NULL,
STREET NVARCHAR2(50) NOT NULL
);

