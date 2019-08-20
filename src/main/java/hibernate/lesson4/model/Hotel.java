package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HOTEL")
public class Hotel {

    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Room> rooms;

    /*
    CREATE TABLE HOTEL(
    HOTEL_ID NUMBER,
    CONSTRAINT HOTEL_PK PRIMARY KEY(HOTEL_ID),
    NAME NVARCHAR2(50) NOT NULL,
    COUNTRY NVARCHAR2(50) NOT NULL,
    CITY NVARCHAR2(50) NOT NULL,
    STREET NVARCHAR2(50) NOT NULL
    );

    CREATE SEQUENCE HOTEL_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;
    */

    @Id
    @SequenceGenerator(name = "HT_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HT_SEQ")
    @Column(name = "HOTEL_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    //    @OneToMany(targetEntity = Room.class, cascade = CascadeType.ALL, mappedBy = "hotel")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hotel")
    public List<Room> getRooms() {
        return rooms;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setRooms(List rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if (rooms != null) {
            rooms = new ArrayList<>();
            room.setHotel(this);
            this.rooms.add(room);
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
