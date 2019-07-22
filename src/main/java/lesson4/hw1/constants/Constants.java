package lesson4.hw1.constants;

public class Constants {

    /*
    for DB
     */
    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "main";
    public static final String PASS = "q2301299266";

    /*
    for FileDAO
     */
    public static final String SQL_REQUEST_FILE_SAVE = "INSERT INTO FILES VALUES(?, ?, ?, ?)";
    public static final String SQL_REQUEST_FILE_DELETE = "DELETE FROM FILES WHERE FILE_ID = ?";
    public static final String SQL_REQUEST_FILE_UPDATE = "UPDATE FILES SET NAME = ?, FORMAT = ?, FILE_SIZE = ?, STORAGE_ID = ? WHERE FILE_ID = ?";
    public static final String SQL_REQUEST_FILE_FIND_BY_ID = "SELECT * FROM FILES WHEN FILE_ID = ?";
    public static final String SQL_REQUEST_FILE_FIND_BY_STORAGE_ID = "SELECT * FROM FILES WHEN STORAGE_ID = ?";

    /*
    for StorageDAO
     */
    public static final String SQL_REQUEST_STORAGE_SAVE = "INSERT INTO STORAGE VALUES(?, ?, ?, ?)";
    public static final String SQL_REQUEST_STORAGE_DELETE = "DELETE FROM STORAGE WHERE STORAGE_ID = ?";
    public static final String SQL_REQUEST_STORAGE_UPDATE = "UPDATE STORAGE SET FORMAT_SUPPORTED = ?, STORAGE_COUNTRY = ?, STORAGE_MAX_SIZE = ? WHERE STORAGE_ID = ?";
    public static final String SQL_REQUEST_STORAGE_FIND_BY_ID = "SELECT * FROM STORAGE WHEN STORAGE_ID = ?";
}