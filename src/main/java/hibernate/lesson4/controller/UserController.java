package hibernate.lesson4.controller;

import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.exceptions.UserNotFoundException;
import hibernate.lesson4.model.*;
import hibernate.lesson4.services.UserService;

public class UserController {
    private UserService userService = new UserService();

    /*
    findHotelByName(String name)
    findHotelByCity(String city)
    Collection findRooms(Filter filter)
    void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
    void cancelReservation(long roomId, long userId)
    registerUser(User user)
    void login(String userName, String password)
    void logout()
    */

    /*
    for users
    */

    public void registerUser(User user) throws Exception {
        userService.registerUser(user);
        //TODO по сути этот метод аналогичен saveUser, поэтому сделал его void
    }

    public void login(String userName, String password) throws Exception {
        validate(userName, password);
        User user = userService.login(userName, password);
        Session.setAuthorized(user);
    }

    public void logout() throws Exception{
        userService.logout();
    }

    /*
    for users,
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

    private void validate(String userName, String password) throws Exception {
        if (userName == null || userName.isEmpty())
            throw new UserNotFoundException("User does not exist");
        if (password == null || password.isEmpty())
            throw new BadRequestException("Password is wrong");
    }
}
