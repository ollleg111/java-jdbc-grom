package lesson2.hw4;

import java.sql.*;
import java.util.ArrayList;

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
        try{
            solution.changeDescription();
        }catch (Exception e){
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

    public void changeDescription() throws Exception{
        ArrayList<Product> productsWithNeedDescription = selectData();

        if (productsWithNeedDescription != null) {
            for (Product product : productsWithNeedDescription) {
                ArrayList<String> withOutLastString = new ArrayList<>();

                String str = product.getDescription();
                String[] sentences = str.split("\\.");

                for (int i = 0; i < sentences.length; i++) {
                    withOutLastString.add(sentences[i]);
                }
                if (withOutLastString.size() > 0)
                    withOutLastString.remove(withOutLastString.size() - 1);

                product.setDescription(withOutLastString.toString());
            }

            for (Product product : productsWithNeedDescription) {
                updateData(product);
            }
        }
    }

    private ArrayList<Product> selectData() throws Exception{
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_REQUEST_SELECT_DESCRIPTION);
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
        throw new Exception("method selectData() returned null");
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