package hibernate.lesson4.demo;

import hibernate.lesson4.controller.OrderController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoOrder {

    public static void main(String[] args) {

        User user = new User("Igor", "123", "UA", UserType.ADMIN);

        UserController userController = new UserController();

        try {
            userController.login(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        OrderController orderController = new OrderController();

        try {
            Date dateFrom = new SimpleDateFormat("dd-MM-yyyy").parse("25-08-2019");
            Date dateTo = new SimpleDateFormat("dd-MM-yyyy").parse("30-08-2019");

            orderController.bookRoom(102, 22, dateFrom, dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            orderController.cancelReservation(102, 22);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
