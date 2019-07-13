package lesson3.hw1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    private static final String SQL_REQUEST_FIND_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
    private static final String SQL_REQUEST_FIND_BY_NAME = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
    private static final String SQL_REQUEST_FIND_WITH_EMPTY_DESCRIPTION = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NOT NULL";


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

    public List<Product> findProductsByPrice(int price, int delta) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_BY_PRICE)) {
            //SELECT * FROM PRODUCT WHERE PRICE BETWEEN 100 AND 300;

            preparedStatement.setInt(1, (price - delta));
            preparedStatement.setInt(2, (price + delta));

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findProductsByName(String word) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_BY_NAME)) {
            //SELECT * FROM PRODUCT WHERE NAME LIKE '%test%';

            preparedStatement.setString(1, "%" + word + "%");
            ResultSet resultSet = preparedStatement.executeQuery();


            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findProductsWithEmptyDescription() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_REQUEST_FIND_WITH_EMPTY_DESCRIPTION);
            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }
}
