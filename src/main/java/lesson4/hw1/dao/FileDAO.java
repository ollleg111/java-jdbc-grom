package lesson4.hw1.dao;

import lesson4.hw1.constants.Constants;
import lesson4.hw1.exception.BadRequestException;
import lesson4.hw1.model.File;
import lesson4.hw1.model.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    private StorageDAO storageDAO = new StorageDAO();

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
        private String name;
        private String format;
        private long size;
        private Storage storage;

        FILE_ID NUMBER,
        CONSTRAINT FILE_PK PRIMARY KEY(FILE_ID),
        NAME NVARCHAR2(20) NOT NULL,
        FORMAT NVARCHAR2(20) NOT NULL,
        FILE_SIZE NUMBER NOT NULL,
        STORAGE_ID NUMBER,
        CONSTRAINT STORAGE_FK FOREIGN KEY (STORAGE_ID) REFERENCES STORAGE(STORAGE_ID)
    */

    public File save(File file) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_SAVE)) {
            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());

            //Файл может храниться в системе, но быть без хранилища.
            preparedStatement.setObject(5, null);

            int result = preparedStatement.executeUpdate();
            System.out.println("File with id: " + file.getId() + " was saved with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + FileDAO.class.getName() +
                    " in method save");
            e.printStackTrace();
        }
        return file;
    }

    public void delete(long id) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_DELETE)) {
            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();
            if (result == 0) throw new BadRequestException("File with id: " + id + "from class: " +
                    FileDAO.class.getName() + " not found");

            System.out.println("File with id: " + id + "deleted with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + FileDAO.class.getName() +
                    " in method delete");
            e.printStackTrace();
        }
    }

    public File update(File file) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_UPDATE)) {
            preparedStatement.setString(1, file.getName());
            preparedStatement.setString(2, file.getFormat());
            preparedStatement.setLong(3, file.getSize());

            if (file.getStorage() != null) {
                preparedStatement.setLong(4, file.getStorage().getId());
            } else {
                preparedStatement.setObject(4, null);
            }

            int result = preparedStatement.executeUpdate();

            if (result == 0) throw new BadRequestException("File with id: " + file.getId() + "from class: " +
                    FileDAO.class.getName() + " not found");

            System.out.println("File with id: " + file.getId() + "updated with result" + result);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + FileDAO.class.getName() +
                    " in method update");
            e.printStackTrace();
        }
        return file;
    }

    public File findById(long id) throws Exception {

        File file = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                file = new File(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getLong(4));
                file.setStorage(storageDAO.findById(resultSet.getLong(5)));

            }
            return file;

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + FileDAO.class.getName() +
                    " in method findById");
            e.printStackTrace();
        }
        throw new BadRequestException("method findById(long id) from class: "
                + FileDAO.class.getName() + " returned null");
    }

    public List<File> getFilesByStorageId(long id) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_FIND_BY_STORAGE_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<File> files = new ArrayList<>();
            while (resultSet.next()) {
                File file = new File(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getLong(4));
                file.setStorage(storageDAO.findById(id));
                files.add(file);
            }
            return files;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        throw new Exception("method getFilesByStorageId(long id) from class: " + FileDAO.class.getName() +
                " returned null");
    }

    public File put(Storage storage, File file) throws Exception {
        file.setStorage(storage);
        return update(file);
    }

    public List<File> putAll(Storage storage, List<File> list) throws Exception {
//TODO
        return null;
    }

    public void delete(File file) throws Exception {
        file.setStorage(null);
        update(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
//TODO
    }

    public void transferFile(Storage storageTo, long id) throws Exception {
        FileDAO fileDAO = new FileDAO();
        File file = fileDAO.findById(id);
        file.setStorage(storageTo);
        fileDAO.update(file);
    }
}
