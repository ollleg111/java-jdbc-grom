package jdbc.lesson2;

import java.sql.*;
import java.util.Date;

public class JDBCSecondStep {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + JDBC_DRIVER + " not found");
                return;
            }

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE PRICE > 200")) {
                while (resultSet.next()) {
                    /*
                        private long id;
                        private String productName;
                        private int price;
                        private Date dateOrdered;
                        private Date dateConfirmed;
                     */

                    /*
                        ID NUMBER,
                        CONSTRAINT ORDER_ID PRIMARY KEY (ID),
                        PRODUCT_NAME NVARCHAR2(20),
                        PRICE NUMBER,
                        DATE_ORDERED TIMESTAMP,
                        DATE_CONFIRMED TIMESTAMP
                     */

                    long id = resultSet.getLong(1);
                    String productName = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    Date dateOrdered = resultSet.getDate(4);
                    Date dateConfirmed = resultSet.getDate(5);

                    Orders orders = new Orders(id, productName, price, dateOrdered, dateConfirmed);
                    System.out.println(orders);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
