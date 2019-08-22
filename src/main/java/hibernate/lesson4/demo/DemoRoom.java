package hibernate.lesson4.demo;

import hibernate.lesson4.controller.RoomController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.util.Date;

public class DemoRoom {

    public static void main(String[] args) {

        User user = new User("IgoR", "1234", "UA", UserType.ADMIN);

        UserController userController = new UserController();

        try {
            userController.login(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        RoomController roomController = new RoomController();

//        try {
//            Room room = new Room(2, 200, 1, 0, new Date());
//            roomController.addRoom(room,101);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        try {
//            Room room = new Room(3, 1200, 1, 1, new Date());
//            roomController.addRoom(room, 101);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            Room room = new Room(5, 10000, 1, 0, new Date());
//            roomController.addRoom(room, 101);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println(roomController.findRoomById(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
