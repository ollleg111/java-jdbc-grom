package hibernate.lesson3.controller;

import hibernate.lesson3.model.Hotel;
import hibernate.lesson3.model.Room;
import hibernate.lesson3.services.HotelService;
import hibernate.lesson3.services.RoomService;

public class Controller {
    private HotelService hotelService = new HotelService();
    private RoomService roomService = new RoomService();

    /*
    for Hotel
    */
    public Hotel saveHotel(Hotel object) throws Exception {
        return hotelService.save(object);
    }

    public void deleteHotel(long id) throws Exception {
        hotelService.delete(id);
    }

    public Hotel updateHotel(Hotel object) throws Exception {
        return hotelService.update(object);
    }

    public Hotel findHotelById(long id) throws Exception {
        return hotelService.findById(id);
    }

    /*
    for Room
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
