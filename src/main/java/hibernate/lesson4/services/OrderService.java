package hibernate.lesson4.services;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.model.Order;

import java.util.Date;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
       //TODO
    }

    public void cancelReservation(long roomId, long userId) {
        //TODO
    }

    public Order save(Order object) throws Exception {
        return orderDAO.save(object);
    }

    public void delete(long id) throws Exception {
        orderDAO.delete(id);
    }

    public Order update(Order object) throws Exception {
        return orderDAO.update(object);
    }

    public Order findById(long id) throws Exception {
        return orderDAO.findById(id);
    }
}
