package hibernate.lesson4.services;

import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static hibernate.lesson4.controller.SessionAuthorization.validateUser;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public Order save(Order object) throws Exception {
        SessionAuthorization.validateUser();
        return orderDAO.save(object);
    }

    public void delete(long id) throws Exception {
        SessionAuthorization.validateUser();
        orderDAO.delete(id);
    }

    public Order update(Order object) throws Exception {
        SessionAuthorization.validateUser();
        return orderDAO.update(object);
    }

    public Order findById(long id) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("an user have not got permission in method" +
                    " findById(long id) from class " +
                    OrderService.class.getName());
        return orderDAO.findById(id);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {

        SessionAuthorization.validateUser();

        Room room = roomDAO.findById(roomId);
        validateBooking(room, dateFrom, dateTo);
        //TODO решил послать в валидацию два раза заглушку, только для теста комнаты на null

        room.setDateAvailableFrom(dateTo);

        User user = userDAO.findById(userId);
        Order order = new Order();
        order.setRoom(room);
        order.setUserOrdered(user);
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setMoneyPaid(room.getPrice() * ChronoUnit.DAYS.between(dateFrom.toInstant(), dateTo.toInstant()));

        orderDAO.booking(order, room);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        SessionAuthorization.validateUser();

        Room room = roomDAO.findById(roomId);
        validateBooking(room, new Date(), new Date());

        Order order = orderDAO.findOrderByUserId(userId);
        //TODO один user - один заказ, если он бронирует много - то надо переделать в лист

        if (order == null) throw new BadRequestException("the user with this id: " + userId +
                " do not have any orders");

        orderDAO.cancelReservation(order, room);
    }

    private void validateBooking(Room room, Date dateFrom, Date dateTo) throws Exception {
        if (room == null)
            throw new BadRequestException("a room with this id does not exist");

        if (dateFrom == null || dateTo == null)
            throw new BadRequestException("wrong dates");

        if (room.getDateAvailableFrom().after(dateFrom))
            throw new BadRequestException("that room with id: " + room.getId() + " was already booked");
    }
}
