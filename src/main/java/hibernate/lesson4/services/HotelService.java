package hibernate.lesson4.services;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.model.Hotel;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();

    /*
    findHotelByName(String name)
    findHotelByCity(String city)
     */

    public Hotel findHotelByName(String name) throws Exception {
        validate(name);
        return hotelDAO.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws Exception {
        validate(city);
        return hotelDAO.findHotelByName(city);
    }

    public Hotel save(Hotel object) throws Exception {
        return hotelDAO.save(object);
    }

    public void delete(long id) throws Exception {
        hotelDAO.delete(id);
    }

    public Hotel update(Hotel object) throws Exception {
        return hotelDAO.update(object);
    }

    public Hotel findById(long id) throws Exception {
        return hotelDAO.findById(id);
    }

    private void validate(String str) throws Exception {
        if (str == null)
            throw new BadRequestException("You did not write searching field");
        //TODO
    }
}
