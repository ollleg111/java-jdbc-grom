package hibernate.lesson4.demo;

import hibernate.lesson4.controller.OrderController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

public class DemoOrder {

        public static void main(String[] args) {

            User user = new User("IgoR", "1234", "UA", UserType.ADMIN);

            UserController userController = new UserController();

            try {
                userController.login(user.getUserName(), user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }

            OrderController orderController = new OrderController();
        }
}
