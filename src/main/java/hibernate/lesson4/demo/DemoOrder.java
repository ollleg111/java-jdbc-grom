package hibernate.lesson4.demo;


import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;

import java.util.Date;

public class DemoOrder {
    public static void main(String[] args) {

        Hotel hotel1 = new Hotel();
        hotel1.setCity("Dnipro");
        hotel1.setCountry("Ukraine");
        hotel1.setName("Alpha");
        hotel1.setStreet("First");

        Room room3 = new Room();
        room3.setBreakfastIncluded(1);
        room3.setPetsAllowed(1);
        room3.setDateAvailableFrom(new Date());
        room3.setHotel(hotel1);
        room3.setNumberOgGuests(2);
        room3.setPrice(1000);

        Room room4 = new Room();
        room4.setBreakfastIncluded(1);
        room4.setPetsAllowed(0);
        room4.setDateAvailableFrom(new Date());
        room4.setHotel(hotel1);
        room4.setNumberOgGuests(2);
        room4.setPrice(1200);


    }
}
