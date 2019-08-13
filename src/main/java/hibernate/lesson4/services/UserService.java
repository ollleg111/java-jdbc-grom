package hibernate.lesson4.services;

import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user) {
        return userDAO.registerUser(user);
    }

    public void login(String userName, String password) {
        userDAO.login(userName, password);
    }

    public void logout() {
        userDAO.logout();
    }

    public User save(User object) throws Exception {
        return userDAO.save(object);
    }

    public void delete(long id) throws Exception {
        userDAO.delete(id);
    }

    public User update(User object) throws Exception {
        return userDAO.update(object);
    }

    public User findById(long id) throws Exception {
        return userDAO.findById(id);
    }
}
