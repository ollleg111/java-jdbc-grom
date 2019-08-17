package hibernate.lesson4.controller;

import hibernate.lesson4.model.*;
import hibernate.lesson4.services.RoomService;

import java.util.List;

public class RoomController {
    private RoomService roomService = new RoomService();

    /*
    Collection findRooms(Filter filter)
    */
    public List<Room> findRooms(Filter filter) throws Exception {
        return roomService.findRooms(filter);
    }

    public Room addRoom(Room room, long hotelId) throws Exception {
        return roomService.addRoom(room, hotelId);
    }

    /*
    CRUD for entity - Room
    */
    public Room saveRoom(Room object) throws Exception {
        return roomService.save(object);
    }

    public void deleteRoom(long id) throws Exception {
        roomService.delete(id);
    }

    public Room updateRoom(Room object) throws Exception {
        return roomService.update(object);
    }

    public Room findRoomById(long id) throws Exception {
        return roomService.findById(id);
    }
}
