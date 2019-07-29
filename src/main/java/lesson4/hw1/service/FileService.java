package lesson4.hw1.service;

import lesson4.hw1.constants.Constants;
import lesson4.hw1.dao.FileDAO;
import lesson4.hw1.exception.BadRequestException;
import lesson4.hw1.model.File;
import lesson4.hw1.model.Storage;

import java.sql.*;
import java.util.List;

public class FileService {
    private FileDAO fileDAO = new FileDAO();

    /*
    CRUD
     */
    public File save(File file) {
        return fileDAO.save(file);
    }

    public void delete(long id) throws Exception {
        fileDAO.delete(id);
    }

    public File update(File file) throws Exception {
        return fileDAO.update(file);
    }

    public File findById(long id) throws Exception {
        return fileDAO.findById(id);
    }

    /*
    other methods
     */
    public File put(Storage storage, File file) throws Exception {
            checkFormat(storage, file);
            checkFreeSpace(storage, file);
            return fileDAO.put(storage, file);
    }

    public List<File> putAll(Storage storage, List<File> list) throws Exception {
            long listSize = 0;
            for (File file : list) {
                listSize += file.getSize();
            }

            if ((storage.getStorageMaxSize() - requestAmount(storage)) < listSize)
                throw new Exception("Storage: " + storage.getId() + " is lower than list. Transfer is impossible");
            return fileDAO.putAll(storage, list);
    }

    public void delete(Storage storage, File file) throws Exception {
            checkFileInStorage(storage, file);
            fileDAO.delete(file);
    }

    /*
    Файл может храниться в системе, но быть без хранилища.
    При этом у файла не может быть больше 1 хранилища одновременно
    Storage может хранить файлы только поддерживаемого формата
    Учитывайте макс размер хранилища
    В одном хранилище не могут хранится файлы с одинаковым айди, но могут хранится файлы с одинаковыми именами
    */

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
            if (storageFrom.getStorageMaxSize() > storageFrom.getStorageMaxSize())
                throw new Exception("Storage: " + storageFrom.getId() + " is bigger than storage: " +
                        +storageTo.getId() + ". Transfer is impossible");

            fileDAO.transferAll(fileDAO.getFilesByStorageId(storageFrom.getId()), storageTo.getId());
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
            File file = fileDAO.findById(id);

            checkFileInStorage(storageFrom, file);
            checkFormat(storageFrom, file);
            checkFreeSpace(storageTo, file);

            fileDAO.transferFile(storageTo, id);
    }

    private void checkFileInStorage(Storage storage, File file) throws Exception {
        if (file.getStorage() == null || file.getStorage().getId() != storage.getId()) {
            throw new Exception("Storage: " + storage.getId() + " do not have file with id: " + file.getId());
        }
    }

    private void checkFormat(Storage storage, File file) throws Exception {
        if (!storage.isSupportedFormat(file.getFormat())) {
            throw new Exception("File with id: " + file.getId() + " does not put in storage: " + storage.getId() +
                    " Format not supported");
        }
    }

    private void checkFreeSpace(Storage storage, File file) throws Exception {
        if ((requestAmount(storage) + file.getSize()) <= storage.getStorageMaxSize())
            throw new Exception("Do not have space for file: " +
                file.getId() + " in storage: " + storage.getId());
    }

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

    private long requestAmount(Storage storage) throws Exception {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(Constants.SQL_REQUEST_STORAGE_FIND_SUM)) {
            preparedStatement.setLong(1, storage.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.getLong(1);

        } catch (SQLException e) {
            System.err.println("Something went wrong in class: " + FileService.class.getName() +
                    " in method requestAmount(Storage storage)");
            e.printStackTrace();
        }
        throw new Exception("method requestAmount(Storage storage) from class: "
                + FileService.class.getName() + " returned null");
    }
}
