package hibernate.lesson4.demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.RoomController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.util.Date;

public class DemoRoom {

    public static void main(String[] args) {

        User user = new User("Igor", "123", "UA", UserType.ADMIN);

        UserController userController = new UserController();

        try {
            userController.login(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RoomController roomController = new RoomController();
        HotelController hotelController = new HotelController();

//        Room room1, room2, room3;
//        try {
//            room1 = new Room(11, 200, 1, 0, new Date());
//            room2 = new Room(12, 200, 1, 0, new Date());
//            room3 = new Room(13, 200, 1, 0, new Date());
//            roomController.addRoom(room1, 2);
//            roomController.addRoom(room2, 2);
//            roomController.addRoom(room3, 2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            Room room = new Room(300, 1200, 1, 1, new Date(),
//                    hotelController.findHotelById(101));
//            roomController.saveRoom(room);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            System.out.println(roomController.findRoomById(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Filter filter = new Filter(1,100D,true,false,"UA","Dnipro","A");
        try {
            System.out.println(roomController.findRooms(filter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
