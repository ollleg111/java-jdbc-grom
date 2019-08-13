package hibernate.lesson4.controller;

import hibernate.lesson4.model.*;
import hibernate.lesson4.services.HotelService;
import hibernate.lesson4.services.OrderService;
import hibernate.lesson4.services.RoomService;
import hibernate.lesson4.services.UserService;

import java.util.Date;
import java.util.List;

public class Controller {
    private HotelService hotelService = new HotelService();
    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();
    private OrderService orderService = new OrderService();

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
    for admins
    */
    public Hotel findHotelByName(String name){
        return hotelService.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city){
        return hotelService.findHotelByCity(city);
    }

    public List findRooms(Filter filter) {
        return roomService.findRooms(filter);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        roomService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) {
        roomService.cancelReservation(roomId, userId);
    }

    public User registerUser(User user) {
        return userService.registerUser(user);
    }

    public void login(String userName, String password) {
        userService.login(userName, password);
    }

    public void logout() {
        userService.logout();
    }

    /*
    for users,
    CRUD for entity - Hotel
    */
    public Hotel saveHotel(Hotel object) throws Exception {
        return hotelService.save(object);
    }

    public void deleteHotel(long id) throws Exception {
        hotelService.delete(id);
    }

    public Hotel updateHotel(Hotel object) throws Exception {
        return hotelService.update(object);
    }

    public Hotel findHotelById(long id) throws Exception {
        return hotelService.findById(id);
    }

    /*
    for users,
    CRUD for entity - Room
    */
    public Room saveRoom(Room object) throws Exception {
        return roomService.save(object);
    }

    public void deleteRoom(long id) throws Exception {
        roomService.delete(id);
    }

    public Room updateRoom(Room object) throws Exception {
        return roomService.update(object);
    }

    public Room findRoomById(long id) throws Exception {
        return roomService.findById(id);
    }

    /*
    for users,
    CRUD for entity - User
    */
    public User saveRoom(User object) throws Exception {
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

    /*
    for users,
    CRUD for entity - Order
    */
    public Order saveRoom(Order object) throws Exception {
        return orderService.save(object);
    }

    public void deleteOrder(long id) throws Exception {
        orderService.delete(id);
    }

    public Order updateUser(Order object) throws Exception {
        return orderService.update(object);
    }

    public Order findOrderById(long id) throws Exception {
        return orderService.findById(id);
    }
}
