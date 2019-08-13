package hibernate.lesson4.dao;

import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        setTypeParameterClass(Room.class);
    }

    /*
    Collection findRooms(Filter filter)
    void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
    void cancelReservation(long roomId, long userId)
    */

    public List findRooms(Filter filter) {
        ArrayList<Room> rooms = new ArrayList<>();
        //TODO
        return rooms;
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        //TODO
    }

    public void cancelReservation(long roomId, long userId){
        //TODO
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
