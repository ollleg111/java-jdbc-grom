package hibernate.lesson4.dao;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        setTypeParameterClass(Hotel.class);
    }

    public List<Hotel> findHotelByName(String name) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Hotel> query = session.createNativeQuery(Constants.FIND_BY_CONTAINED_NAME, Hotel.class);
            query.setParameter(1, "%" + name + "%");

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findHotelByName(String name) method  from class " +
                    HotelDAO.class.getName() + " was failed");
        }
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Hotel> query = session.createNativeQuery(Constants.FIND_BY_CITY, Hotel.class);
            query.setParameter(1, city);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findHotelByCity(String city) method from class " +
                    HotelDAO.class.getName() + " was failed");
        }
    }

    @Override
    public Hotel save(Hotel object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(long id) throws Exception {
        super.delete(id);
    }

    @Override
    public Hotel update(Hotel object) throws Exception {
        return super.update(object);
    }

    @Override
    public Hotel findById(long id) throws Exception {
        return super.findById(id);
    }
}
