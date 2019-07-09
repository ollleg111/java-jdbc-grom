package lesson2;

import java.sql.*;
import java.util.Date;

public class JDBCExamples {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

//    static {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class " + JDBC_DRIVER + " not found");
//        }
//    }

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

//            boolean resultSet = statement.execute("INSERT INTO PRODUCT  VALUES(1007, 'test6', 'for children', 135)");
//            System.out.println(resultSet);

            boolean resultSet = statement.execute("DELETE FROM PRODUCT WHERE ID = 1006");
            System.out.println(resultSet);

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE ID = 1007");
            System.out.println(response);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
