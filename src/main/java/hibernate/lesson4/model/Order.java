package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ORDER")
public class Order {
    private long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    /*
    ORDERS_ID NUMBER,
    CONSTRAINT ORDERS_PK PRIMARY KEY(ORDERS_ID),
    USERS_ID NVARCHAR2(50) NOT NULL,
    CONSTRAINT USERS_FK FOREIGN KEY (USERS_ID) REFERENCES USERS(USERS_ID),
    ROOM_ID NVARCHAR2(50) NOT NULL,
    CONSTRAINT ROOM_FK FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID),
    DATE_FROM TIMESTAMP,
    DATE_TO TIMESTAMP,
    MONEY_PAID NUMBER (10,2) NOT NULL
     */

    @Id
    @SequenceGenerator(name = "OR_SEQ", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OR_SEQ")
    @Column(name = "ORDERS_ID")
    public long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    public User getUserOrdered() {
        return userOrdered;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    public Room getRoom() {
        return room;
    }

    @Column(name = "DATE_FROM")
    public Date getDateFrom() {
        return dateFrom;
    }

    @Column(name = "DATE_TO")
    public Date getDateTo() {
        return dateTo;
    }

    @Column(name = "MONEY_PAID")
    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserOrdered(User userOrdered) {
        this.userOrdered = userOrdered;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userOrdered=" + userOrdered +
                ", room=" + room +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", moneyPaid=" + moneyPaid +
                '}';
    }
}
