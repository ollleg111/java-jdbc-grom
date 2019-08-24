package hibernate.lesson4.demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

public class DemoHotel {
    public static void main(String[] args) {

        User user = new User("Igor", "123", "UA", UserType.ADMIN);

        UserController userController = new UserController();

//        try {
//            userController.saveUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            userController.login(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        Hotel hotel1 = new Hotel();
//        hotel1.setCity("Dnipro");
//        hotel1.setCountry("Ukraine");
//        hotel1.setName("Alpha");
//        hotel1.setStreet("First");
//
//        Hotel hotel = new Hotel("Alpha", "UA", "Dnipro", "str. First");
//        Hotel hotel = new Hotel("Zero", "UA", "Kiev", "str. First");

        HotelController hotelController = new HotelController();
//
//        try {
//            hotelController.saveHotel(hotel);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println(hotelController.findHotelByName("Alp"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(hotelController.findHotelById(101));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(hotelController.findHotelByCity("Kiev"));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            hotelController.deleteHotel(3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
