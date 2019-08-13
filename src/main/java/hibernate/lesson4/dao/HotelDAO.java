package hibernate.lesson4.dao;

import hibernate.lesson4.model.Hotel;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        setTypeParameterClass(Hotel.class);
    }

    /*
    findHotelByName(String name)
    findHotelByCity(String city)
    */

    public Hotel findHotelByName(String name){
        Hotel hotel = new Hotel();
        //TODO
        return hotel;
    }

    public Hotel findHotelByCity(String city){
        Hotel hotel = new Hotel();
        //TODO
        return hotel;
    }

    @Override
    public Hotel save(Hotel object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(long id) throws Exception {
        super.delete(id);
    }

    @Override
    public Hotel update(Hotel object) throws Exception {
        return super.update(object);
    }

    @Override
    public Hotel findById(long id) throws Exception {
        return super.findById(id);
    }
}
