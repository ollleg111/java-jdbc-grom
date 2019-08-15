package hibernate.lesson4.services;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;

import java.util.ArrayList;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();


    public ArrayList<Room> findRooms(Filter filter) {
        ArrayList<Room> rooms = new ArrayList<>();
        //TODO
        return rooms;
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
}
