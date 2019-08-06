package hibernate.lesson3.services;

import hibernate.lesson3.dao.RoomDAO;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public Object save(Object object) throws Exception {
        return roomDAO.save(object);
    }

    public void delete(long id) throws Exception {
        roomDAO.delete(id);
    }

    public Object update(Object object) throws Exception {
        return roomDAO.update(object);
    }

    public Object findById(long id) throws Exception {
        return roomDAO.findById(id);
    }
}
