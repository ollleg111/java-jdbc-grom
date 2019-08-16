package hibernate.lesson4.services;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HotelService {

    private HotelDAO hotelDAO = new HotelDAO();
    private static SessionFactory sessionFactory;

    public List<Hotel> findHotelByName(String name) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Hotel> query = session.createNativeQuery(Constants.FIND_BY_CONTAINED_NAME, Hotel.class);
            query.setParameter(1, "%" + name + "%");

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findHotelByName(String name) method  from class " +
                    HotelService.class.getName() + " was failed");
        }
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Hotel> query = session.createNativeQuery(Constants.FIND_BY_NAME, Hotel.class);
            query.setParameter(1, city);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findHotelByCity(String city) method from class " +
                    HotelService.class.getName() + " was failed");
        }
    }

    public Hotel save(Hotel object) throws Exception {
        return hotelDAO.save(object);
    }

    public void delete(long id) throws Exception {
        hotelDAO.delete(id);
    }

    public Hotel update(Hotel object) throws Exception {
        return hotelDAO.update(object);
    }

    public Hotel findById(long id) throws Exception {
        return hotelDAO.findById(id);
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
