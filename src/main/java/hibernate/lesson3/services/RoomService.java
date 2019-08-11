package hibernate.lesson3.services;

import hibernate.lesson3.dao.RoomDAO;
import hibernate.lesson3.model.Room;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

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
}
