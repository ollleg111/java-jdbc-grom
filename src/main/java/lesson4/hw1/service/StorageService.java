package lesson4.hw1.service;

import lesson4.hw1.dao.StorageDAO;
import lesson4.hw1.model.Storage;


public class StorageService {
    private StorageDAO storageDAO = new StorageDAO();

    /*
    CRUD
     */
    public Storage save(Storage storage) throws Exception {
        return storageDAO.save(storage);
    }

    public void delete(long id) throws Exception {
        storageDAO.delete(id);
    }

    public Storage update(Storage storage) throws Exception {
        return storageDAO.update(storage);
    }

    public Storage findById(long id) throws Exception {
        return storageDAO.findById(id);
    }
}
