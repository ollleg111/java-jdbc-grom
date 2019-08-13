package hibernate.lesson4.dao;

import hibernate.lesson4.model.User;

public class UserDAO extends GeneralDAO<User> {

    public UserDAO() {
        setTypeParameterClass(User.class);
    }

    /*
    registerUser(User user)
    void login(String userName, String password)
    void logout()
    */

    public User registerUser(User user) {
        User user1 = new User();
        //TODO
        return user1;
    }

    public void login(String userName, String password) {
        //TODO
    }

    public void logout() {
        //TODO
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
}
