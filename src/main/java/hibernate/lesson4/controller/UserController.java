package hibernate.lesson4.controller;

import hibernate.lesson4.exceptions.UserNotFoundException;
import hibernate.lesson4.model.*;
import hibernate.lesson4.services.UserService;

public class UserController {
    private UserService userService = new UserService();

    /*
    registerUser(User user)
    void login(String userName, String password)
    void logout()
    */
    public void registerUser(User user) throws Exception {
        userService.registerUser(user);
        //TODO по сути этот метод аналогичен saveUser, поэтому сделал его void
    }

    public void login(String userName, String password) throws Exception {
        User user = userService.login(userName, password);

        if (user == null) throw new UserNotFoundException("user with name: " + userName +
                " does not exist in method login(String userName, String password) from class " +
                UserController.class.getName());

        SessionAuthorization.setAuthorized(user);
    }

    public void logout() throws Exception {
        userService.logout();
    }

    public void removeAccount(User user) throws Exception {
        userService.delete(user.getId());
    }

    /*
    CRUD for entity - User
    */
    public User saveUser(User object) throws Exception {
        return userService.save(object);
    }

    public void deleteUser(long id) throws Exception {
        userService.delete(id);
    }

    public User updateUser(User object) throws Exception {
        return userService.update(object);
    }

    public User findUserById(long id) throws Exception {
        return userService.findById(id);
    }
}
