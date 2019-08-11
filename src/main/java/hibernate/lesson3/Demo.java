package hibernate.lesson3;

import hibernate.lesson3.controller.Controller;
import hibernate.lesson3.model.Hotel;
import hibernate.lesson3.model.Room;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        Hotel hotel1 = new Hotel();
        hotel1.setId(101);
        hotel1.setCity("Dnipro");
        hotel1.setCountry("Ukraine");
        hotel1.setName("Alpha");
        hotel1.setStreet("First");

        Room room1 = new Room();
        room1.setId(201);
        room1.setBreakfastIncluded(1);
        room1.setPetsAllowed(1);
        room1.setDateAvailableFrom(new Date());
        room1.setHotel(hotel1);
        room1.setNumberOgGuests(2);
        room1.setPrice(1000);

        Room room2 = new Room();
        room2.setId(202);
        room2.setBreakfastIncluded(1);
        room2.setPetsAllowed(0);
        room2.setDateAvailableFrom(new Date());
        room2.setHotel(hotel1);
        room2.setNumberOgGuests(2);
        room2.setPrice(1200);

        Controller controller = new Controller();

        try {
            controller.saveRoom(room2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.saveHotel(hotel1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            controller.saveRoom(room1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
