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

    public File save(File file){

                try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_SAVE)) {
            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());

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
            if (result == 0) throw new BadRequestException("File with id: " + id + " in method delete(long id)" +
                    " from class: " + FileDAO.class.getName() + " not found");

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
                    " in method findById(long id)");
            e.printStackTrace();
        }
        throw new Exception("method findById(long id) from class: "
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
            System.err.println("Something went wrong in class: " + FileDAO.class.getName() +
                    " in method getFilesByStorageId(long id)");
            e.printStackTrace();
            throw new Exception("Something went wrong in method getFilesByStorageId(long id)." +
                    " Not all items updated. The list is not complete");
        }
    }

    /*
    1
    */
    public File put(Storage storage, File file) throws Exception {
        file.setStorage(storage);
        return update(file);
    }

    /*
    2
    */
    public List<File> putAll(Storage storage, List<File> files) throws Exception {
        try (Connection connection = getConnection()) {
            saveList(files, storage.getId(), connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong in method putAll in class: " + FileDAO.class.getName());
            e.printStackTrace();
        }
        return getFilesByStorageId(storage.getId());
    }

    /*
    3
    */
    public void delete(File file) throws Exception {
        file.setStorage(null);
        update(file);
    }

    /*
    4
    */
    public void transferAll(List<File> files, long id) {
        try (Connection connection = getConnection()) {
            saveList(files, id, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong in method transferAll in class: " + FileDAO.class.getName());
            e.printStackTrace();
        }
    }

    /*
    5
    */
    public void transferFile(Storage storageTo, long id) throws Exception {
        FileDAO fileDAO = new FileDAO();
        File file = fileDAO.findById(id);
        file.setStorage(storageTo);
        fileDAO.update(file);
    }

    private static void saveList(List<File> files, long id, Connection connection) throws SQLException {

        long fileId = 0;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(Constants.SQL_REQUEST_FILE_UPDATE)) {

            connection.setAutoCommit(false);

            for (File file : files) {

                fileId = file.getId();

                preparedStatement.setString(1, file.getName());
                preparedStatement.setString(2, file.getName());
                preparedStatement.setLong(3, file.getSize());
                preparedStatement.setLong(4, id);
                preparedStatement.setLong(5, file.getId());

                int result = preparedStatement.executeUpdate();
                System.out.println("save was finished with result " + result);
            }
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("File with id: " + fileId + " was not transfer in method saveList in class: " +
                    FileDAO.class.getName());
        }
    }
}
