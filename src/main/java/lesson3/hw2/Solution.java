package lesson3.hw2;

import lesson3.hw1.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    /*  Напишите SQL запрос для создания таблицы с полями:
        ID - уникальной идендификатор
        SOME_STRING текстовое поле, которое не может быть пустым
        SOME_NUMBER - целочисельное поле которое не может быть пустым
    */

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    private static final String SQL_REQUEST_CREATE_TABLE_SPEED =
            "CREATE TABLE TEST_SPEED(\n" +
                    "        ID NUMBER NOT NULL,\n" +
                    "        CONSTRAINT SPEED_PK PRIMARY KEY(ID),\n" +
                    "        SOME_STRING NVARCHAR2(20) NOT NULL,\n" +
                    "        SOME_NUMBER NUMBER NOT NULL)";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + JDBC_DRIVER + " not found");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void createTableSpeed() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

                int resultSet = statement.executeUpdate(SQL_REQUEST_CREATE_TABLE_SPEED);
                System.out.println(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
