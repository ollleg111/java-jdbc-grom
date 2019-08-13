package hibernate.lesson4.services;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.model.Order;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

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
