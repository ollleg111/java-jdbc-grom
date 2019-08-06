package hibernate.lesson3.controller;

import hibernate.lesson3.services.HotelService;
import hibernate.lesson3.services.RoomService;

public class Controller {
    private HotelService hotelService = new HotelService();
    private RoomService roomService = new RoomService();

    /*
    for Hotel
    */
    public Object saveHotel(Object object) throws Exception{
        return hotelService.save(object);
    }

    public void deleteHotel(long id) throws Exception{
        hotelService.delete(id);
    }

    public Object updateHotel(Object object) throws Exception{
        return hotelService.update(object);
    }

    public Object findHotelById(long id) throws Exception{
        return hotelService.findById(id);
    }

    /*
    for Room
    */
    public Object saveRoom(Object object) throws Exception {
        return roomService.save(object);
    }

    public void deleteRoom(long id) throws Exception {
        roomService.delete(id);
    }

    public Object updateRoom(Object object) throws Exception {
        return roomService.update(object);
    }

    public Object findRoomById(long id) throws Exception {
        return roomService.findById(id);
    }
}
