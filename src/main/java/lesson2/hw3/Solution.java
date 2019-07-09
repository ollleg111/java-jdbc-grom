package lesson2.hw3;

import lesson2.Product;

import java.sql.*;

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

    /*
    private long id;
    private String name;
    private String description;
    private int price;
     */

    public static void main(String[] args) {

        Solution.getAllProducts();
        Solution.getProductsByPrice();
        Solution.getProductsByDescription();

    }

    public static void getAllProducts() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT")) {
                while (resultSet.next()) {

                    long id = resultSet.getLong("ID");
                    String name = resultSet.getString("NAME");
                    String description = resultSet.getString("DESCRIPTION");
                    int price = resultSet.getInt("PRICE");

                    Product product = new Product(id, name, description, price);
                    System.out.println(product);
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByPrice() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRICE >= 100")) {
                while (resultSet.next()) {

                    long id = resultSet.getLong("ID");
                    String name = resultSet.getString("NAME");
                    String description = resultSet.getString("DESCRIPTION");
                    int price = resultSet.getInt("PRICE");

                    Product product = new Product(id, name, description, price);
                    System.out.println(product);
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByDescription() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50")) {
                while (resultSet.next()) {

                    long id = resultSet.getLong("ID");
                    String name = resultSet.getString("NAME");
                    String description = resultSet.getString("DESCRIPTION");
                    int price = resultSet.getInt("PRICE");

                    Product product = new Product(id, name, description, price);
                    System.out.println(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
