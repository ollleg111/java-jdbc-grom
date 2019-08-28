package hibernate.lesson4.demo;

import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

public class DemoUser {

    public static void main(String[] args) {

//        User user = new User("Igor", "123", "UA", UserType.ADMIN);
        User user1 = new User("Alex", "123", "UA", UserType.USER);

        UserController userController = new UserController();


        try {
            userController.registerUser(user1);
//            userController.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        try {
//            userController.login(user.getUserName(), user.getPassword());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println(userController.findUserById(24));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            userController.deleteUser(29);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            userController.saveUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            userController.logout();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
