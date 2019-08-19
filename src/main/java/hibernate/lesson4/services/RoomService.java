package hibernate.lesson4.services;

import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;

import java.util.List;

import static hibernate.lesson4.controller.SessionAuthorization.validate;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();
    private HotelDAO hotelDAO = new HotelDAO();

    public List<Room> findRooms(Filter filter) throws Exception {
        /*
        private Integer numberOfGuests;
        private Double price;
        private Boolean breakfastIncluded;
        private Boolean petsAllowed;
        private String country;
        private String city;
        private String hotel;
        */

        validate();

        if (filter != null) {
            return roomDAO.findRooms(filter);
        }
        throw new BadRequestException("filter is empty in method findRooms(Filter filter) " +
                " from class " + RoomService.class.getName());
    }

    public Room addRoom(Room room, long hotelId) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("user do not have permission in method addRoom(Room room, long hotelId)" +
                    " from class " + RoomService.class.getName());

        Hotel hotel = hotelDAO.findById(hotelId);
        if (hotel == null) throw new BadRequestException("hotel does not exist in method " +
                "addRoom(Room room, long hotelId) from class " + RoomService.class.getName());

        hotel.addRoom(room);
        hotelDAO.update(hotel);
        return room;
    }

    public Room save(Room object) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("user do not have permission in method save(Room object) from class "
                    + RoomService.class.getName());
        return roomDAO.save(object);
    }

    public void delete(long id) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("user do not have permission in method delete(long id) from class "
                    + RoomService.class.getName());
        roomDAO.delete(id);
    }

    public Room update(Room object) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("user do not have permission in method update(Room object) from class "
                    + RoomService.class.getName());
        return roomDAO.update(object);
    }

    public Room findById(long id) throws Exception {
        validate();
        return roomDAO.findById(id);
    }
}
