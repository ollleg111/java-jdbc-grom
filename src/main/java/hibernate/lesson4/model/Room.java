package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOM")
public class Room {

    private long id;
    private int numberOgGuests;
    private double price;
    private int breakfastIncluded;
    private int petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room() {
    }

    public Room(int numberOgGuests, double price, int breakfastIncluded, int petsAllowed, Date dateAvailableFrom) {
        this.numberOgGuests = numberOgGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
    }

    /*
    CREATE TABLE ROOM(
    ROOM_ID NUMBER,
    CONSTRAINT ROOM_PK PRIMARY KEY(ROOM_ID),
    NUMBER_GUESTS NUMBER DEFAULT 1 NOT NULL,
    PRICE NUMBER (10,2) NOT NULL,
    BREAKFAST NUMBER(1) DEFAULT 0,
    CHECK (BREAKFAST BETWEEN 0 AND 1),
    PETS NUMBER(1) DEFAULT 0,
    CHECK (PETS BETWEEN 0 AND 1),
    DATE_AVAILABLE TIMESTAMP,
    HOTEL_ID NUMBER NOT NULL,
    CONSTRAINT HOTEL_FK FOREIGN KEY (HOTEL_ID) REFERENCES HOTEL(HOTEL_ID)
    );

    CREATE SEQUENCE ROOM_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
    */

    @Id
    @SequenceGenerator(name = "RM_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RM_SEQ")
    @Column(name = "ROOM_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NUMBER_GUESTS")
    public int getNumberOgGuests() {
        return numberOgGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @Column(name = "BREAKFAST")
    public int getBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Column(name = "PETS")
    public int getPetsAllowed() {
        return petsAllowed;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_AVAILABLE")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL_ID")
    public Hotel getHotel() {
        return hotel;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setNumberOgGuests(int numberOgGuests) {
        this.numberOgGuests = numberOgGuests;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public void setBreakfastIncluded(int breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded > 0 ? 1 : 0;
    }


    public void setPetsAllowed(int petsAllowed) {
        this.petsAllowed = petsAllowed > 0 ? 1 : 0;
    }


    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }


    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOgGuests=" + numberOgGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", hotel=" + hotel +
                '}';
    }
}
