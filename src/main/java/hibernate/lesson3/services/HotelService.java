package hibernate.lesson3.services;

import hibernate.lesson3.dao.HotelDAO;

public class HotelService {
    private HotelDAO hotelDAO = new HotelDAO();

    public Object save(Object object) throws Exception {
        return hotelDAO.save(object);
    }

    public void delete(long id) throws Exception {
        hotelDAO.delete(id);
    }

    public Object update(Object object) throws Exception {
        return hotelDAO.update(object);
    }

    public Object findById(long id) throws Exception {
        return hotelDAO.findById(id);
    }
}
