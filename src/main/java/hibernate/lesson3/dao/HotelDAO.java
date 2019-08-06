package hibernate.lesson3.dao;

public class HotelDAO<T> extends GeneralDAO {

    @Override
    public Object save(Object object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(long id) throws Exception {
        super.delete(id);
    }

    @Override
    public Object update(Object object) throws Exception {
        return super.update(object);
    }

    @Override
    public Object findById(long id) throws Exception {
        return super.findById(id);
    }
}
