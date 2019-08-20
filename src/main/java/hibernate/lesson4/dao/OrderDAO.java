package hibernate.lesson4.dao;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;

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

    public void booking(Order order, Room room) throws Exception {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(order);
            session.update(room);

            tr.commit();

        } catch (HibernateException e) {
            System.err.println("booking is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("the method booking(Order order, Room room) from class " +
                    OrderDAO.class.getName() + " was failed");
        }
    }

    public Order findOrderByUserId(long userId) throws Exception {

        try (Session session = createSessionFactory().openSession()) {
            Query<Order> query = session.createNativeQuery(Constants.FIND_ORDER_BY_USER_ID, Order.class);
            query.setParameter(1, userId);

            //SELECT * FROM ORDERS WHERE USERS_ID = ?;

            return query.getSingleResult();

        } catch (HibernateException e) {
            throw new Exception("findOrderByUserId(long userId) method was failed from class " +
                    OrderDAO.class.getName());
        }
    }

    public void cancelReservation(Order order, Room room) throws Exception {

        room.setDateAvailableFrom(new Date());

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.delete(order);
            session.update(room);

            tr.commit();

        } catch (HibernateException e) {
            System.err.println("canceling is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("the method cancelReservation(Order order, Room room) from class " +
                    OrderDAO.class.getName() + " was failed");
        }
    }
}
