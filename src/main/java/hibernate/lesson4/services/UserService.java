package hibernate.lesson4.services;

import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.exceptions.UserNotFoundException;
import hibernate.lesson4.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User login(String userName, String password) throws Exception {

        if (userName == null || password == null) throw
                new UserNotFoundException("userName or password do not enter in method" +
                        " login(String userName, String password) from class " +
                        UserService.class.getName());

        return userDAO.login(userName, password);
    }

    public void logout() throws Exception {
        SessionAuthorization.validateUser();
        SessionAuthorization.setAuthorized(null);
    }

    public User save(User object) throws Exception {
        return userDAO.save(object);
    }

    public void delete(long id) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("an user have not got permission in method" +
                    " delete(long id) from class " +
                    UserService.class.getName());
        userDAO.delete(id);
    }

    public User update(User object) throws Exception {
        SessionAuthorization.validateUser();
        return userDAO.update(object);
    }

    public User findById(long id) throws Exception {
        if (!SessionAuthorization.isAdmin())
            throw new AuthorizedException("an user have not got permission in method" +
                    " findById(long id) from class " +
                    UserService.class.getName());
        return userDAO.findById(id);
    }
}
