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
    private static final String SQL_REQUEST_FIND_WITH_EMPTY_DESCRIPTION = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL";


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

    public List<Product> findProductsByPrice(int price, int delta) throws Exception {

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
        throw new Exception("method findProductsByPrice(int price, int delta) returned null");
    }

    public List<Product> findProductsByName(String word) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_FIND_BY_NAME)) {
            //SELECT * FROM PRODUCT WHERE NAME LIKE '%test%';

            validate(word);

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
        throw new Exception("method findProductsByName(String word) returned null");
    }

    private void validate(String word) throws Exception {
        /*Если word является некоректным (больше одного слова в стринге, длина меньше 3, содержит спецсимволы),
         выбрасывать ошибку, которая в описании обязательно должна содержать само слово и описание ошибки
         */
        if (word.length() < 3) throw new Exception("word`s length " + word + " is less than three symbols");

        for (Character ch : word.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) {
                throw new Exception("word " + word + "is consist special symbols");
            }
        }
    }

    public List<Product> findProductsWithEmptyDescription() throws Exception {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            //SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL;

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
        throw new Exception("method findProductsByName(String word) returned null");
    }
}
