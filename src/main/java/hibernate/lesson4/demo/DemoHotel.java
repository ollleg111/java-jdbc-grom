package hibernate.lesson4.demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.RoomController;
import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.util.Date;

public class DemoHotel {
    public static void main(String[] args) {

        User user = new User();
        user.setCountry("UA");
        user.setPassword("123");
        user.setUserName("Oleg");
        user.setUserType(UserType.ADMIN);

        UserController userController = new UserController();

        try {
            userController.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        try {
//            userController.login(user.getUserName(),user.getPassword());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        SessionAuthorization.getAuthorized();

//        Hotel hotel1 = new Hotel();
//        hotel1.setCity("Dnipro");
//        hotel1.setCountry("Ukraine");
//        hotel1.setName("Alpha");
//        hotel1.setStreet("First");

//        Room room1 = new Room();
//        room1.setBreakfastIncluded(1);
//        room1.setPetsAllowed(1);
//        room1.setDateAvailableFrom(new Date());
//        room1.setHotel(hotel1);
//        room1.setNumberOgGuests(2);
//        room1.setPrice(1000);
//
//        Room room2 = new Room();
//        room2.setBreakfastIncluded(1);
//        room2.setPetsAllowed(0);
//        room2.setDateAvailableFrom(new Date());
//        room2.setHotel(hotel1);
//        room2.setNumberOgGuests(2);
//        room2.setPrice(1200);

        HotelController hotelController = new HotelController();
//        RoomController roomController = new RoomController();

//        try {
//            hotelController.saveHotel(hotel1);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            roomController.saveRoom(room2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            roomController.saveRoom(room1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
