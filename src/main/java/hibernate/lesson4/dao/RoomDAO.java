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
            Query<Room> query = session.createNativeQuery(Constants.FIND_ROOMS_BY_FILTER, Room.class);
            query.setParameter(1, filter);

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
