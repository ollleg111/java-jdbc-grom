package jdbc.lesson3.hw3;

import java.sql.*;
import java.util.ArrayList;

public class Solution {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.chwj6uiyvsme.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "q2301299266";

    private static final String SQL_REQUEST_SAVE_PERFORMANCE = "INSERT INTO TEST_SPEED VALUES(?, ?, ?)";
    private static final String SQL_REQUEST_DELETE_BY_ID_PERFORMANCE = "DELETE FROM TEST_SPEED WHERE ID = ?";
    private static final String SQL_REQUEST_DELETE_PERFORMANCE = "DELETE FROM TEST_SPEED;";
    private static final String SQL_REQUEST_SELECT_BY_ID_PERFORMANCE = "SELECT * FROM TEST_SPEED WHERE ID = ?";
    private static final String SQL_REQUEST_SELECT_PERFORMANCE = "SELECT * FROM TEST_SPEED";

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
      testSavePerformance() - который будет успешно добавлять 1000 записей в таблицу TEST_SPEED c
       произвольными значениями
    */
    public void testSavePerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_SAVE_PERFORMANCE)) {

            ArrayList<TestSpeed> arr = new ArrayList<>();

            for (int i = 1; i <= 1000; i++) {
                arr.add(new TestSpeed(i, (" some text with number " + String.valueOf(i)), (int) (Math.random() * 1000)));
            }
            long startTime = System.currentTimeMillis();

            for (TestSpeed testSpeed : arr) {
                preparedStatement.setLong(1, testSpeed.getId());
                preparedStatement.setString(2, testSpeed.getSomeString());
                preparedStatement.setInt(3, testSpeed.getSomeNumber());

                preparedStatement.executeUpdate();
            }
            long finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /*
      testDeleteByIdPerformance() - будет удалять 1000 добавленных перед этим записей, отдельными
      запросами по полю ID
    */
    public void testDeleteByIdPerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_BY_ID_PERFORMANCE)) {

            long startTime = System.currentTimeMillis();

            for (int i = 1; i <= 1000; i++) {
                preparedStatement.setLong(1, i);

                preparedStatement.executeUpdate();
            }
            long finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /*
      testDeletePerformance - будет удалять 1000, одним SQL запросом()
    */
    public void testDeletePerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_DELETE_PERFORMANCE)) {

            long startTime = System.currentTimeMillis();

            preparedStatement.executeUpdate();

            long finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /*
      testSelectByIdPerformance() - будет выбирать по очереди 1000 добавленных перед этим записей,
       отдельными запросами по полю ID
    */
    public void testSelectByIdPerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_SELECT_BY_ID_PERFORMANCE)) {

            long startTime = System.currentTimeMillis();

            for (int i = 1; i <= 1000; i++) {
                preparedStatement.setLong(1, i);

                preparedStatement.executeUpdate();
            }
            long finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /*
      testSelectPerformance() - будет выбирать 1000 записей, одним SQL запросом
    */
    public void testSelectPerformance() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REQUEST_SELECT_PERFORMANCE)) {

            long startTime = System.currentTimeMillis();

            preparedStatement.executeUpdate();

            long finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
