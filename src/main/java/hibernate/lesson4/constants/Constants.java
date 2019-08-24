package hibernate.lesson4.constants;

public class Constants {

    public static final String FIND_HOTEL_BY_CONTAINED_NAME = "SELECT * FROM HOTEL WHERE NAME LIKE ?";
    public static final String FIND_HOTEL_BY_CITY           = "SELECT * FROM HOTEL WHERE CITY = ?";
    public static final String FIND_USER_BY_NAME_AND_PASS   = "SELECT * FROM USERS WHERE USER_NAME = ?" +
            " AND USER_PASS = ?";
    public static final String FIND_ORDER_BY_USER_ID        = "SELECT * FROM ORDERS WHERE USERS_ID = ?";
    public static final String FIND_ROOMS_BY_FILTER         =
                    "SELECT * FROM ROOM " +
                            "JOIN HOTEL ON ROOM.HOTEL_ID = HOTEL.HOTEL_ID AND " +
                            "HOTEL.COUNTRY = ? AND " +
                            "HOTEL.CITY = ? AND " +
                            "HOTEL.NAME LIKE 'A%' AND " +
                            "ROOM.BREAKFAST = ? AND " +
                            "ROOM.PETS  = ? AND " +
                            "ROOM.NUMBER_GUESTS = ? AND " +
                            "ROOM.PRICE = ?";
}
