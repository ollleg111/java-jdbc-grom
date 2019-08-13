package hibernate.lesson4.services;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomService {
    private RoomDAO roomDAO = new RoomDAO();

    public List findRooms(Filter filter) {
        return roomDAO.findRooms(filter);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        roomDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        if ((Long) roomId != null || ((Long) userId) != null)
            roomDAO.cancelReservation(roomId, userId);
        throw new BadRequestException("You did not write canceled field");
        //TODO
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
