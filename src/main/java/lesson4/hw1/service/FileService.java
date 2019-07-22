package lesson4.hw1.service;

import lesson4.hw1.dao.FileDAO;
import lesson4.hw1.model.File;
import lesson4.hw1.model.Storage;

import java.util.List;

public class FileService {
    private FileDAO fileDAO = new FileDAO();

    /*
    CRUD
     */
    public File save(File file) throws Exception {
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

        if (storage != null && file != null) {

            checkFormat(storage, file);
            if (!checkFreeSpace(storage, file)) throw new Exception("For file: " + file.getId() +
                    " do not have needed space in storage: " + storage.getId());
            return fileDAO.put(storage, file);
        }
        throw new Exception("Storage or File not found");
    }

    public List<File> putAll(Storage storage, List<File> list) throws Exception {


        return fileDAO.putAll(storage, list);
    }

    public void delete(Storage storage, File file) throws Exception {

        if (storage != null && file != null) {
            checkFileInStorage(storage, file);
            fileDAO.delete(storage, file);
        }
        throw new Exception("Storage or File not found");
    }

    /*
    Файл может храниться в системе, но быть без хранилища. При этом у файла не может быть больше 1 хранилища одновременно
    Storage может хранить файлы только поддерживаемого формата
    Учитывайте макс размер хранилища
    В одном хранилище не могут хранится файлы с одинаковым айди, но могут хранится файлы с одинаковыми именами
    */

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        if (storageFrom != null && storageTo != null) {
            if (storageFrom.getStorageMaxSize() > storageFrom.getStorageMaxSize())
                throw new Exception("Storage: " + storageFrom.getId() + " is bigger than storage: " +
                        +storageTo.getId() + ". Transfer is impossible");

            fileDAO.transferAll(storageFrom, storageTo);
        }
        throw new Exception("StorageFrom or StorageTo not found");
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        if (storageFrom != null && storageTo != null && fileDAO.findById(id) != null) {
            File file = fileDAO.findById(id);

            checkFileInStorage(storageFrom, file);
            checkFormat(storageFrom, file);
            checkFreeSpace(storageTo, file);

            fileDAO.transferFile(storageTo, id);
        }
        throw new Exception("StorageFrom or StorageTo or File not found");
    }

    private void checkFileInStorage(Storage storage, File file) throws Exception {

        if (file.getStorage() == null || file.getStorage().getId() != storage.getId()) {
            throw new Exception("Storage: " + storage.getId() + " do not have file with id: " + file.getId());
        }
    }

    private void checkFormat(Storage storage, File file) throws Exception {
        if (!storage.isSupportedFormat(file.getFormat())) {
            throw new Exception("File with id: " + file.getId() + " does not put in storage: " + storage.getId());
        }
    }

    private boolean checkFreeSpace(Storage storage, File file) throws Exception {
        long size = 0;
        for (File f : fileDAO.getFilesByStorageId(storage.getId())) {
            size += f.getSize();
        }

        if ((size + file.getSize()) <= storage.getStorageMaxSize()) {
            return true;
        }
        return false;
    }
}
