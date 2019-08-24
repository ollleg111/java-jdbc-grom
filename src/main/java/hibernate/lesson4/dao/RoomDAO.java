package hibernate.lesson4.dao;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.exceptions.BadRequestException;
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

        if (filter.getCountry() == null || filter.getCity() == null || filter.getHotelName() == null ||
                filter.getBreakfastIncluded() == 0 || filter.getPetsAllowed() == 0 ||
                filter.getNumberOfGuests() == 0 || filter.getPrice() == null) {

            try (Session session = createSessionFactory().openSession()) {
                Query<Room> query = session.createNativeQuery(Constants.FIND_ROOMS_BY_FILTER, Room.class);

                query.setParameter(1, filter.getCountry());
                query.setParameter(2, filter.getCity());
                query.setParameter(3, "%" + filter.getHotelName() + "%");
                query.setParameter(4, filter.getBreakfastIncluded());
                query.setParameter(5, filter.getPetsAllowed());
                query.setParameter(6, filter.getNumberOfGuests());
                query.setParameter(7, filter.getPrice());

                return query.list();

            } catch (HibernateException e) {
                throw new Exception("the method findRooms(Filter filter) from class " +
                        RoomDAO.class.getName() + " was failed");
            }
        }
        throw new BadRequestException("one or some fields in class Filter are null");
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
