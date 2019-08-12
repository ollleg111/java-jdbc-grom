package hibernate.lesson3.model;

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

    @Column(name = "DATE_AVAILABLE")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL_ID")
//    @Column(name = "HOTEL_ID")
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
