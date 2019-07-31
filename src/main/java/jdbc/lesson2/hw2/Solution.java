package jdbc.lesson2.hw2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + JDBC_DRIVER + " not found");
        }
    }

    /*
    ID NUMBER NOT NULL ENABLE,
    CONSTRAINT PRODUCT_PK PRIMARY KEY (ID),
    NAME NVARCHAR2(20) NOT NULL,
    DESCRIPTION CLOB NOT NULL,
    PRICE NUMBER NOT NULL
     */

    public static void main(String[] args) {

        Solution.saveProduct();
        Solution.deleteProduct();
        Solution.deletedProductsByPrice();

    }

    public static void saveProduct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int resultSet = statement.executeUpdate("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES(999, 'toy', 'for children', 50)");
            System.out.println(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void deleteProduct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME != 'toy'");
            System.out.println(response);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void deletedProductsByPrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE PRICE < 100");
            System.out.println(response);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
