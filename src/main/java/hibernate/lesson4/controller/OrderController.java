package hibernate.lesson4.controller;

import hibernate.lesson4.model.*;
import hibernate.lesson4.services.HotelService;
import hibernate.lesson4.services.OrderService;
import hibernate.lesson4.services.RoomService;
import hibernate.lesson4.services.UserService;

import java.util.ArrayList;
import java.util.Date;

public class OrderController {
    private OrderService orderService = new OrderService();

    /*
    findHotelByName(String name)
    findHotelByCity(String city)
    Collection findRooms(Filter filter)
    void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
    void cancelReservation(long roomId, long userId)
    registerUser(User user)
    void login(String userName, String password)
    void logout()
    */

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        orderService.cancelReservation(roomId, userId);
    }

    /*
    for users,
    CRUD for entity - Order
    */
    public Order saveOrder(Order object) throws Exception {
        return orderService.save(object);
    }

    public void deleteOrder(long id) throws Exception {
        orderService.delete(id);
    }

    public Order updateUser(Order object) throws Exception {
        return orderService.update(object);
    }

    public Order findOrderById(long id) throws Exception {
        return orderService.findById(id);
    }
}
