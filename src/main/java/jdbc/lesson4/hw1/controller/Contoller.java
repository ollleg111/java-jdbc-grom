package jdbc.lesson4.hw1.controller;

import jdbc.lesson4.hw1.model.File;
import jdbc.lesson4.hw1.model.Storage;
import jdbc.lesson4.hw1.service.FileService;
import jdbc.lesson4.hw1.service.StorageService;

import java.util.List;

/*
CREATE TABLE STORAGE(
STORAGE_ID NUMBER,
CONSTRAINT STORAGE_PK PRIMARY KEY(STORAGE_ID),
FORMAT_SUPPORTED NVARCHAR2(50) NOT NULL,
STORAGE_COUNTRY NVARCHAR2(50) NOT NULL,
STORAGE_MAX_SIZE NUMBER NOT NULL
);

-- с именем FILE таблица не создается, пишет
-- Error report -
-- ORA-00903: invalid table name
-- 00903. 00000 -  "invalid table name"

-- тоже самое с полем SIZE, пишет
-- Error report -
-- ORA-00904: : invalid identifier
-- 00904. 00000 -  "%s: invalid identifier"

CREATE TABLE FILES(
FILE_ID NUMBER,
CONSTRAINT FILE_PK PRIMARY KEY(FILE_ID),
NAME NVARCHAR2(20) NOT NULL,
FORMAT NVARCHAR2(20) NOT NULL,
FILE_SIZE NUMBER NOT NULL,
STORAGE_ID NUMBER,
CONSTRAINT STORAGE_FK FOREIGN KEY (STORAGE_ID) REFERENCES STORAGE(STORAGE_ID)
);
 */

public class Contoller {
    private FileService fileService = new FileService();
    private StorageService storageService = new StorageService();

    /*
    for FileDAO
    */
    public File save(File file) {
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
    public Storage save(Storage storage) {
        return storageService.save(storage);
    }

    public void deleteStorage(long id) throws Exception {
        storageService.delete(id);
    }

    public Storage update(Storage storage) throws Exception {
        return storageService.update(storage);
    }

    public Storage findStorageById(long id) throws Exception {
        return storageService.findById(id);
    }
}
