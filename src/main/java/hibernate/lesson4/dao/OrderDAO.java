package hibernate.lesson4.dao;


import hibernate.lesson4.model.Order;

public class OrderDAO extends GeneralDAO<Order> {

    public OrderDAO() {
        setTypeParameterClass(Order.class);
    }

    @Override
    public Order save(Order object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(long id) throws Exception {
        super.delete(id);
    }

    @Override
    public Order update(Order object) throws Exception {
        return super.update(object);
    }

    @Override
    public Order findById(long id) throws Exception {
        return super.findById(id);
    }
}
