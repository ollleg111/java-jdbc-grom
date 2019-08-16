package hibernate.lesson4.constants;

public class Constants {

    public static final String FIND_BY_CONTAINED_NAME      = "SELECT * FROM HOTELS WHERE NAME LIKE ?";
    public static final String FIND_BY_NAME                = "SELECT * FROM HOTELS WHERE NAME = ?";
    public static final String FIND_ROOMS_BY_FILTER        = "SELECT * FROM ROOMS WHERE NAME = ?";
    public static final String FIND_USER_BY_NAME_AND_PASS  = "SELECT * FROM USERS WHERE NAME = ?";


}
