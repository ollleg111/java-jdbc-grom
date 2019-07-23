package lesson4.hw1.dao;

import lesson4.hw1.constants.Constants;
import lesson4.hw1.exception.BadRequestException;
import lesson4.hw1.model.Storage;

import java.sql.*;

public class StorageDAO {

    static {
        try {
            Class.forName(Constants.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + Constants.JDBC_DRIVER + " not found");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
    }

    /*
        private long id;
        private String[] formatsSupported;
        private String storageCountry;
        private long storageMaxSize;

        FILE_ID NUMBER,
        CONSTRAINT STORAGE_PK PRIMARY KEY(STORAGE_ID),
        FORMAT_SUPPORTED NVARCHAR2(50) NOT NULL,
        STORAGE_COUNTRY NVARCHAR2(50) NOT NULL,
        STORAGE_MAX_SIZE NUMBER NOT NULL
    */

    public Storage save(Storage storage) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_STORAGE_SAVE)) {
            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, storage.fromTableToString());
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageMaxSize());

            int result = preparedStatement.executeUpdate();
            System.out.println("save was finished with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + StorageDAO.class.getName() +
                    " in method save");
            e.printStackTrace();
        }
        return storage;
    }

    public void delete(long id) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_STORAGE_DELETE)) {
            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();

            if (result == 0) throw new BadRequestException("File with id: " + id + "from class: " +
                    Storage.class.getName() + " not found");

            System.out.println("delete was finished with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + StorageDAO.class.getName() +
                    " in method delete");
            e.printStackTrace();
        }
    }

    public Storage update(Storage storage) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_STORAGE_UPDATE)) {
            preparedStatement.setString(1, storage.fromTableToString());
            preparedStatement.setString(2, storage.getStorageCountry());
            preparedStatement.setLong(3, storage.getStorageMaxSize());
            preparedStatement.setLong(4, storage.getId());

            int result = preparedStatement.executeUpdate();

            if (result == 0) throw new BadRequestException("File with id: " + storage.getId() +
                    "from class: " +
                    Storage.class.getName() + " not found");

            System.out.println("update was finished with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + StorageDAO.class.getName() +
                    " in method update");
            e.printStackTrace();
        }
        return storage;
    }

    public Storage findById(long id) throws Exception {
        Storage storage = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_STORAGE_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String[] strArray = resultSet.getString(2).split(", ");

                storage = new Storage(
                        resultSet.getLong(1),
                        strArray,
                        resultSet.getString(3),
                        resultSet.getInt(4));

            }
            return storage;

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + StorageDAO.class.getName() +
                    " in method findById");
            e.printStackTrace();
        }
        throw new BadRequestException("method findById(long id) from class: "
                + StorageDAO.class.getName() + " returned null");
    }
}
