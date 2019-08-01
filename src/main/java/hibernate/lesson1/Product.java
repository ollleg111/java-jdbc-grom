package hibernate.lesson1;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {
    /*
        ID NUMBER NOT NULL ENABLE,
        CONSTRAINT PRODUCT_PK PRIMARY KEY (ID),
        NAME NVARCHAR2(20) NOT NULL,
        DESCRIPTION CLOB NOT NULL,
        PRICE NUMBER NOT NULL
    */

    /*
        CREATE SEQUENCE PRODUCT_SEQ MINVALUE 1 MAXVALUE 100 START WITH 1 INCREMENT BY 2;
        or
        CREATE SEQUENCE PRODUCT_SEQ INCREMENT BY 1 MAXVALUE 100 CYCLE;
        DROP SEQUENCE PRODUCT_SEQ;
    */

    private long id;
    private String name;
    private String description;
    private int price;

    @Id
    @SequenceGenerator(name = "PRD_SEQ", sequenceName = "PRODUCTS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRD_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
