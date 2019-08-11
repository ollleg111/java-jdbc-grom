package hibernate.lesson3.dao;

import hibernate.lesson3.model.Room;

public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        setTypeParameterClass(Room.class);
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
