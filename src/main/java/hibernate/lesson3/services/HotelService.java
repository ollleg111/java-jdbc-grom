package hibernate.lesson3.services;

import hibernate.lesson3.dao.HotelDAO;
import hibernate.lesson3.model.Hotel;

public class HotelService{
    private HotelDAO hotelDAO = new HotelDAO();

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
}
