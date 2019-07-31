package jdbc.lesson3.hw3;

public class TestSpeed {

    /*  ID - уникальной идендификатор
        SOME_STRING текстовое поле, которое не может быть пустым
        SOME_NUMBER - целочисельное поле которое не может быть пустым
     */

    private long id;
    private String someString;
    private int someNumber;

    public TestSpeed(long id, String someString, int someNumber) {
        this.id = id;
        this.someString = someString;
        this.someNumber = someNumber;
    }

    public long getId() {
        return id;
    }

    public String getSomeString() {
        return someString;
    }

    public int getSomeNumber() {
        return someNumber;
    }

    @Override
    public String toString() {
        return "TestSpeed{" +
                "id=" + id +
                ", someString='" + someString + '\'' +
                ", someNumber=" + someNumber +
                '}';
    }
}
