package hibernate.lesson2.hw3;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {
    /*
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
