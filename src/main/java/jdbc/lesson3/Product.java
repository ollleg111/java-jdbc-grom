package jdbc.lesson3;

public class Product {
        /*
        ID NUMBER NOT NULL ENABLE,
        CONSTRAINT PRODUCT_PK PRIMARY KEY (ID),
        NAME NVARCHAR2(20) NOT NULL,
        DESCRIPTION CLOB NOT NULL,
        PRICE NUMBER NOT NULL
     */

    private long id;
    private String name;
    private String description;
    private int price;

    public Product(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductOne{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
