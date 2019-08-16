package hibernate.lesson4.controller;

import hibernate.lesson4.model.*;
import hibernate.lesson4.services.HotelService;
import hibernate.lesson4.services.OrderService;
import hibernate.lesson4.services.RoomService;
import hibernate.lesson4.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelController {
    private HotelService hotelService = new HotelService();

    /*
    findHotelByName(String name)
    findHotelByCity(String city)
    */
    public List<Hotel> findHotelByName(String name) throws Exception {
        return hotelService.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        return hotelService.findHotelByCity(city);
    }

    /*
    for users,
    CRUD for entity - Hotel
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
}
