package hibernate.lesson4.controller;

import hibernate.lesson4.model.Order;
import hibernate.lesson4.services.OrderService;

import java.util.Date;

public class OrderController {
    private OrderService orderService = new OrderService();

    /*
    void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
    void cancelReservation(long roomId, long userId)
    */
    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {
        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        orderService.cancelReservation(roomId, userId);
    }

    /*
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
