package lesson2.hw4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    private static final String SQL_REQUEST_UPDATE_PRICE = "UPDATE PRODUCT SET PRICE = (PRICE + 100) WHERE PRICE < 970";
    private static final String SQL_REQUEST_SELECT_DESCRIPTION = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 100";
    private static final String SQL_REQUEST_UPDATE_DESCRIPTION = "UPDATE PRODUCT SET DESCRIPTION = ? WHERE ID = ?";

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

    /*
    ID NUMBER NOT NULL ENABLE,
    CONSTRAINT PRODUCT_PK PRIMARY KEY (ID),
    NAME NVARCHAR2(20) NOT NULL,
    DESCRIPTION CLOB NOT NULL,
    PRICE NUMBER NOT NULL
     */

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.increasePrice();
        try {
            solution.changeDescription();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void increasePrice() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            int resultSet = statement.executeUpdate(SQL_REQUEST_UPDATE_PRICE);
            System.out.println(resultSet);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void changeDescription() throws Exception {
        ArrayList<Product> productsWithNeedDescription = selectData();

        /*
        for (Product product : productsWithNeedDescription) {
                ArrayList<String> withOutLastString = new ArrayList<>();

                String str = product.getDescription();
                String[] sentences = str.split("\\.");

                Collections.addAll(withOutLastString, sentences);
                if (withOutLastString.size() > 0)
                    withOutLastString.remove(withOutLastString.size() - 1);

                product.setDescription(withOutLastString.toString());
            }

            for (Product product : productsWithNeedDescription) {
                updateData(product);
            }
         */


        for (Product product : productsWithNeedDescription) {
            String[] sentences = product.getDescription().split("\\.");
            String[] sentencesCut = partArray(sentences, sentences.length - 1);
            product.setDescription(String.join("", sentencesCut));
            updateData(product);
        }
    }

    private String[] partArray(String[] array, int size) {
        String[] part = new String[size];
        System.arraycopy(array, 0, part, 0, size);
        return part;
    }

    private ArrayList<Product> selectData() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_REQUEST_SELECT_DESCRIPTION);

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            throw new Exception("Something went wrong in method selectData(). Not all items from " +
                    "DESCRIPTION writes on the list. The list is not complete");
        }
        return products;
    }

    private Product updateData(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_UPDATE_DESCRIPTION)) {
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setLong(2, product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return product;
    }
}