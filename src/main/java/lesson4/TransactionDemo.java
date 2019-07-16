package lesson4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    private static final String SQL_REQUEST_INSERT = "INSERT INTO PRODUCT VALUES(?, ?, ?, ?)";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + JDBC_DRIVER + " not found");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_INSERT)) {

            connection.setAutoCommit(false);

            for (Product product : products) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int result = preparedStatement.executeUpdate();
                System.out.println("save was finished with result " + result);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}

