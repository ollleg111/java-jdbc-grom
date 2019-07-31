package jdbc.lesson1;

import java.sql.*;

public class JDBCFirstStep {

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

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST")) {
                while (resultSet.next()) {
//                    int id = resultSet.getInt("ID");
//                    String one = resultSet.getString("ONE");
//                    String two = resultSet.getString("TWO");
//                    String three = resultSet.getString("THREE");
//
//                    System.out.println("id:    " + id);
//                    System.out.println("one:   " + one);
//                    System.out.println("two:   " + two);
//                    System.out.println("three: " + three);
//                    System.out.println();

//                    System.out.println("Object found");


                    System.out.println(resultSet.getInt(1));
                    System.out.println(resultSet.getString(2));

                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }
}
