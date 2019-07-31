

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