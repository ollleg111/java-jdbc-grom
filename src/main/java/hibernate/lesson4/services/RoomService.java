package hibernate.lesson4.services;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

import static hibernate.lesson4.services.HotelService.createSessionFactory;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();
//    private static SessionFactory sessionFactory;

    public List<Room> findRooms(Filter filter) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Room> query = session.createNativeQuery(Constants.FIND_ROOMS_BY_FILTER, Room.class);
            query.setParameter(1, filter);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findRooms(Filter filter) method from class " +
                    RoomService.class.getName() + " was failed");
        }
    }

    public Room save(Room object) throws Exception {
        return roomDAO.save(object);
    }

    public void delete(long id) throws Exception {
        roomDAO.delete(id);
    }

    public Room update(Room object) throws Exception {
        return roomDAO.update(object);
    }

    public Room findById(long id) throws Exception {
        return roomDAO.findById(id);
    }

//    public static SessionFactory createSessionFactory() {
//        if (sessionFactory == null) {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        }
//        return sessionFactory;
//    }
}
