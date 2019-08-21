package hibernate.lesson4.services;

import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.model.Hotel;

import java.util.List;

public class HotelService {

    private HotelDAO hotelDAO = new HotelDAO();

    public List<Hotel> findHotelByName(String name) throws Exception {
        SessionAuthorization.validateUser();
        return  hotelDAO.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        SessionAuthorization.validateUser();
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel save(Hotel object) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("an user have not got permission in method" +
                    " save(Hotel object) from class " +
                    HotelService.class.getName());
        return hotelDAO.save(object);
    }

    public void delete(long id) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("an user have not got permission in method" +
                    " delete(long id) from class " +
                    HotelService.class.getName());
        hotelDAO.delete(id);
    }

    public Hotel update(Hotel object) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("an user have not got permission in method" +
                    " update(Hotel object) from class " +
                    HotelService.class.getName());
        return hotelDAO.update(object);
    }

    public Hotel findById(long id) throws Exception {
        SessionAuthorization.validateUser();
        return hotelDAO.findById(id);
    }
}
