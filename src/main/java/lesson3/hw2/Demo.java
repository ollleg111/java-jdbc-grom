package lesson3.hw2;

public class Demo {
    /*
        CREATE TABLE TEST_SPEED(
        ID NUMBER NOT NULL,
        CONSTRAINT SPEED_PK PRIMARY KEY(ID),
        SOME_STRING NVARCHAR2(20) NOT NULL,
        SOME_NUMBER NUMBER NOT NULL);
     */

    public static void main(String[] args) {
       Solution solution = new Solution();
       solution.createTableSpeed();
    }
}
