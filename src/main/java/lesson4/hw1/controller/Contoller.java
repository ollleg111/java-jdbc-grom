package lesson4.hw1.controller;

import lesson4.hw1.model.File;
import lesson4.hw1.model.Storage;
import lesson4.hw1.service.FileService;
import lesson4.hw1.service.StorageService;

import java.util.List;

public class Contoller {
    private FileService fileService = new FileService();
    private StorageService storageService = new StorageService();

    /*
    for FileDAO
    */
    public File save(File file) throws Exception {
        return fileService.save(file);
    }

    public void deleteFile(long id) throws Exception {
        fileService.delete(id);
    }

    public File update(File file) throws Exception {
        return fileService.update(file);
    }

    public File findFileById(long id) throws Exception {
        return fileService.findById(id);
    }

    public File put(Storage storage, File file) throws Exception {
        return fileService.put(storage, file);
    }

    public List<File> putAll(Storage storage, List<File> list) throws Exception {
        return fileService.putAll(storage, list);
    }

    public void delete(Storage storage, File file) throws Exception {
        fileService.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        fileService.transferAll(storageFrom, storageTo);
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        fileService.transferFile(storageFrom, storageTo, id);
    }

    /*
    for StorageDAO
    */
    public Storage save(Storage storage) throws Exception {
        return storageService.save(storage);
    }

    public void deleteStroage(long id) throws Exception {
        storageService.delete(id);
    }

    public Storage update(Storage storage) throws Exception {
        return storageService.update(storage);
    }

    public Storage findStorageById(long id) throws Exception {
        return storageService.findById(id);
    }
}
