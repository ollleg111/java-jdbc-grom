package hibernate.lesson4.services;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.util.Date;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {

        Room room = roomDAO.findById(roomId);
        User user = userDAO.findById(userId);

        Order order = new Order();
        order.setRoom(room);
        order.setUserOrdered(user);
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);

        orderDAO.save(order);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
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
