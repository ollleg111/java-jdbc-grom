package jdbc.lesson4.hw1.model;

import java.util.Arrays;

public class Storage {

    /*
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

    private long id;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageMaxSize;

    public Storage(long id, String[] formatsSupported, String storageCountry, long storageMaxSize) {
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageMaxSize() {
        return storageMaxSize;
    }


    public String fromTableToString() {
        String format = Arrays.toString(getFormatsSupported());
        return format.substring(1, format.length() - 2);
    }

    public boolean isSupportedFormat(String format){
        for(String str: formatsSupported){
            if(str.equals(format))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        if (storageMaxSize != storage.storageMaxSize) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(formatsSupported, storage.formatsSupported)) return false;
        return storageCountry != null ? storageCountry.equals(storage.storageCountry) : storage.storageCountry == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + Arrays.hashCode(formatsSupported);
        result = 31 * result + (storageCountry != null ? storageCountry.hashCode() : 0);
        result = 31 * result + (int) (storageMaxSize ^ (storageMaxSize >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize=" + storageMaxSize +
                '}';
    }
}
