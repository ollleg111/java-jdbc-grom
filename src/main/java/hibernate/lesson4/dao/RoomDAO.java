package hibernate.lesson4.dao;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        setTypeParameterClass(Room.class);
    }

    public List<Room> findRooms(Filter filter) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Room> query = session.createQuery(Constants.FIND_ROOMS_BY_FILTER, Room.class);

            /*
            "FROM Room R WHERE " +
            'R.COUNTRY = :country AND " +
            "R.HOTEL.CITY = :city AND " +
            "R.HOTEL.NAME LIKE :name AND " +
            "(R.BREAKFAST = :breakfastOne OR R.BREAKFAST = :breakfastTwo) AND " +
            "(R.PETS = :petsOne OR R.PETS = :petsTwo) AND " +
            "R.NUMBER_GUESTS >= :numberGuests AND " +
            "R.PRICE <= :price " +
            "ORDER BY R.COUNTRY ASC";
            */

            //--------strings

            if (filter.getCountry() != null) {
                query.setParameter("country", "%" + filter.getCountry() + "%");
            } else {
                query.setParameter("country", "%%");
            }
            if (filter.getCity() != null) {
                query.setParameter("city", "%" + filter.getCity() + "%");
            } else {
                query.setParameter("city", "%%");
            }
            if (filter.getHotelName() != null) {
                query.setParameter("name", "%" + filter.getHotelName() + "%");
            } else {
                query.setParameter("name", "%%");
            }

            //--------booleans

            if (filter.isBreakfastIncluded() != null) {
                query.setParameter("breakfastOne", filter.getBreakfastIncluded());
                query.setParameter("breakfastTwo", filter.getBreakfastIncluded());
            } else {
                query.setParameter("breakfastOne", 0);
                query.setParameter("breakfastTwo", 1);
            }
            if (filter.isPetsAllowed() != null) {
                query.setParameter("petsOne", filter.getPetsAllowed());
                query.setParameter("petsTwo", filter.getPetsAllowed());
            } else {
                query.setParameter("petsOne", 0);
                query.setParameter("petsTwo", 1);
            }

            //--------numbers

            if (filter.getNumberOfGuests() == null) {
                query.setParameter("numberGuests", filter.getNumberOfGuests());
            } else {
                query.setParameter("numberGuests", 0);
            }
            if (filter.getPrice() == null) {
                query.setParameter("price", filter.getPrice());
            } else {
                query.setParameter("price", (double) 1000000);
            }

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findRooms(Filter filter) method from class " +
                    RoomDAO.class.getName() + " was failed");
        }
    }

    @Override
    public Room save(Room object) throws Exception {
        return super.save(object);
    }

    @Override
    public Room update(Room object) throws Exception {
        return super.update(object);
    }

    @Override
    public void delete(long id) throws Exception {
        super.delete(id);
    }

    @Override
    public Room findById(long id) throws Exception {
        return super.findById(id);
    }
}
