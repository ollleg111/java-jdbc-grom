package hibernate.lesson4.constants;

public class Constants {

    public static final String FIND_HOTEL_BY_CONTAINED_NAME = "SELECT * FROM HOTEL WHERE NAME LIKE ?";
    public static final String FIND_HOTEL_BY_CITY           = "SELECT * FROM HOTEL WHERE CITY = ?";
    public static final String FIND_USER_BY_NAME_AND_PASS   = "SELECT * FROM USERS WHERE USER_NAME = ?" +
            " AND USER_PASS = ?";
    public static final String FIND_ORDER_BY_USER_ID        = "SELECT * FROM ORDERS WHERE USERS_ID = ?";
    public static final String FIND_ROOMS_BY_FILTER         =
                    "FROM Room AS R WHERE " +
                    "R.COUNTRY = :country AND " +
                    "R.HOTEL.CITY = :city AND " +
                    "R.HOTEL.NAME LIKE :name AND " +
                    "(R.BREAKFAST = :breakfastOne OR R.BREAKFAST = :breakfastTwo) AND " +
                    "(R.PETS = :petsOne OR R.PETS = :petsTwo) AND " +
                    "R.NUMBER_GUESTS >= :numberGuests AND " +
                    "R.PRICE <= :price " +
                    "ORDER BY R.COUNTRY ASC";
}
