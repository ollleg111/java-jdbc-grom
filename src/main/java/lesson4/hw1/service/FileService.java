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

        if (storage != null && file != null) {

            checkFormat(storage, file);
            checkFreeSpace(storage, file);

            return fileDAO.put(storage, file);
        }
        throw new Exception("Storage or File is null in method put in class: " + FileService.class.getName());
    }

    public List<File> putAll(Storage storage, List<File> list) throws Exception {

        if (storage != null && list != null) {
            long listSize = 0;
            for (File file : list) {
                listSize += file.getSize();
            }

            if ((storage.getStorageMaxSize() - requestAmount(storage)) < listSize)
                throw new Exception("Storage: " + storage.getId() + " is lower than list. Transfer is impossible");

            return fileDAO.putAll(storage, list);
        }
        throw new Exception("storage or list is null in method putAll in class: " +
                FileService.class.getName());
    }

    public void delete(Storage storage, File file) throws Exception {

        if (storage != null && file != null) {
            checkFileInStorage(storage, file);

            fileDAO.delete(file);
        }
        throw new Exception("Storage or File is null in method putAll in class: " + FileService.class.getName());
    }

    /*
    Файл может храниться в системе, но быть без хранилища.
    При этом у файла не может быть больше 1 хранилища одновременно
    Storage может хранить файлы только поддерживаемого формата
    Учитывайте макс размер хранилища
    В одном хранилище не могут хранится файлы с одинаковым айди, но могут хранится файлы с одинаковыми именами
    */

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        if (storageFrom != null && storageTo != null) {
            if (storageFrom.getStorageMaxSize() > storageFrom.getStorageMaxSize())
                throw new Exception("Storage: " + storageFrom.getId() + " is bigger than storage: " +
                        +storageTo.getId() + ". Transfer is impossible");

            fileDAO.transferAll(fileDAO.getFilesByStorageId(storageFrom.getId()), storageTo.getId());
        }
        throw new Exception("StorageFrom or StorageTo is null in method transferAll in class: " +
                FileService.class.getName());
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        if (storageFrom != null && storageTo != null && fileDAO.findById(id) != null) {
            File file = fileDAO.findById(id);

            checkFileInStorage(storageFrom, file);
            checkFormat(storageFrom, file);
            checkFreeSpace(storageTo, file);

            fileDAO.transferFile(storageTo, id);
        }
        throw new Exception("StorageFrom or StorageTo or File is null in method transferFile in class: "
                + FileService.class.getName());
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
        if (!checkSpace(storage, file)) throw new Exception("Do not have space for file: " +
                file.getId() + " in storage: " + storage.getId());
    }

    private boolean checkSpace(Storage storage, File file) throws Exception {

        return (requestAmount(storage) + file.getSize()) <= storage.getStorageMaxSize();
    }

    private long requestAmount(Storage storage) throws Exception {
        long size = 0;
        for (File f : fileDAO.getFilesByStorageId(storage.getId())) {
            size += f.getSize();
        }
        return size;
    }
}
