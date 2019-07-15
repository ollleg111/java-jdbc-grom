package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    private static final String SQL_REQUEST_INSERT = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";
    private static final String SQL_REQUEST_SELECT = "SELECT * FROM PRODUCT";
    private static final String SQL_REQUEST_UPDATE = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?";
    private static final String SQL_REQUEST_DELETE = "DELETE FROM PRODUCT WHERE ID = ?";

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

    public Product save(Product product) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_INSERT)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int result = preparedStatement.executeUpdate();
            System.out.println("save was finished with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }


    public List<Product> getProducts() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_REQUEST_SELECT);
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

    public void delete(long id) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE)) {
            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();

            if (result == 0) throw new Exception("Product with id: " + id + " not found");

            System.out.println("delete was finished with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public Product update(Product product) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_UPDATE)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int result = preparedStatement.executeUpdate();

            if (result == 0) throw new Exception("Product with id: " + product.getId() + " not found");

            System.out.println("update was finished with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }
}
