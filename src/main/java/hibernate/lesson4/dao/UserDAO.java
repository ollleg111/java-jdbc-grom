package hibernate.lesson4.dao;

import hibernate.lesson4.model.User;

public class UserDAO extends GeneralDAO<User> {

    public UserDAO() {
        setTypeParameterClass(User.class);
    }

    @Override
    public User save(User object) throws Exception {
        return super.save(object);
    }

    @Override
    public void delete(long id) throws Exception {
        super.delete(id);
    }

    @Override
    public User update(User object) throws Exception {
        return super.update(object);
    }

    @Override
    public User findById(long id) throws Exception {
        return super.findById(id);
    }

    public User findUserByNameAndPassword(String userName, String password) {
        User user = new User();
        //TODO
        return user;
    }
}
