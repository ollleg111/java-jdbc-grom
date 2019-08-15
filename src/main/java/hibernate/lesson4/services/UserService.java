package hibernate.lesson4.services;

import hibernate.lesson4.controller.Session;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user) throws Exception {
        return save(user);
    }

    public User login(String userName, String password) throws Exception {
        if (userName != null && password != null) {
            userDAO.findUserByNameAndPassword(userName, password);
        }
        throw new BadRequestException("userName or password do not enter");
    }

    public void logout() throws AuthorizedException {
        if (Session.getAuthorized() == null) throw new AuthorizedException("User is not authorized");
        Session.setAuthorized(null);
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
