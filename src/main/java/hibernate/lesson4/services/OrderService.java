package hibernate.lesson4.services;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.util.Date;

import static hibernate.lesson4.controller.SessionAuthorization.validate;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {

        validate();

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

        validate();

        //TODO
    }

    public Order save(Order object) throws Exception {
        validate();
        return orderDAO.save(object);
    }

    public void delete(long id) throws Exception {
        validate();
        orderDAO.delete(id);
    }

    public Order update(Order object) throws Exception {
        validate();
        return orderDAO.update(object);
    }

    public Order findById(long id) throws Exception {
        if (!UserDAO.isAdmin())
            throw new AuthorizedException("user do not have permission in method findById(long id) from class " +
                    OrderService.class.getName());
        //TODO ????
        return orderDAO.findById(id);
    }
}
