package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List orders;

    /*
    CREATE TABLE USERS(
    USERS_ID NUMBER,
    CONSTRAINT USERS_PK PRIMARY KEY(USERS_ID),
    USER_NAME NVARCHAR2(50) NOT NULL,
    USER_PASS NVARCHAR2(50) NOT NULL,
    USER_COUNTRY NVARCHAR2(50) NOT NULL,
    USER_TYPE NVARCHAR2(5) DEFAULT 'USER' NOT NULL,
    CHECK (USER_TYPE = 'ADMIN' OR USER_TYPE = 'USER')
    );

    CREATE SEQUENCE USERS_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
    */

    @Id
    @SequenceGenerator(name = "US_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "US_SEQ")
    @Column(name = "USERS_ID")
    public long getId() {
        return id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name = "USER_PASS")
    public String getPassword() {
        return password;
    }

    @Column(name = "USER_COUNTRY")
    public String getCountry() {
        return country;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, mappedBy = "userOrdered")
//    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, mappedBy = "userOrdered")
    @JoinColumn(name = "ORDERS_ID")
    public List getOrders() {
        return orders;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", userType=" + userType +
                ", orders=" + orders +
                '}';
    }
}
