package hibernate.lesson4.controller;

import hibernate.lesson4.model.*;
import hibernate.lesson4.services.HotelService;
import hibernate.lesson4.services.OrderService;
import hibernate.lesson4.services.RoomService;
import hibernate.lesson4.services.UserService;

import java.util.ArrayList;
import java.util.Date;

public class RoomController {
    private RoomService roomService = new RoomService();

    /*
    findHotelByName(String name)
    findHotelByCity(String city)
    Collection findRooms(Filter filter)
    void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
    void cancelReservation(long roomId, long userId)
    registerUser(User user)
    void login(String userName, String password)
    void logout()
    */

    /*
    for users
    */

    public ArrayList<Room> findRooms(Filter filter) {
        if (filter != null)
            return roomService.findRooms(filter);
        return null;
    }

    /*
    for users,
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
