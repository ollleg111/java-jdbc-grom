package hibernate.lesson4.services;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exceptions.AuthorizedException;
import hibernate.lesson4.exceptions.BadRequestException;
import hibernate.lesson4.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import static hibernate.lesson4.services.HotelService.createSessionFactory;

public class UserService {

    private UserDAO userDAO = new UserDAO();
//    private static SessionFactory sessionFactory;

    public User registerUser(User user) throws Exception {
        return save(user);
    }

    public User login(String userName, String password) throws Exception {

        if (userName == null || password == null) throw
                new BadRequestException("userName or password do not enter in method " +
                        "login(String userName, String password) from class " +
                        UserService.class.getName());

        try (Session session = createSessionFactory().openSession()) {
            Query<User> query = session.createNativeQuery(Constants.FIND_USER_BY_NAME_AND_PASS, User.class);
            query.setParameter(1, userName);
            query.setParameter(2, password);

            return query.getSingleResult();

        } catch (HibernateException e) {
            throw new Exception("login(String userName, String password) method was failed from class " +
                    UserService.class.getName());
        }
    }

    public void logout() throws AuthorizedException {
        if (SessionAuthorization.getAuthorized() == null) throw
                new AuthorizedException("User is not authorized" +
                "in method logout() from class " +
                        UserService.class.getName());
        SessionAuthorization.setAuthorized(null);
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

//    public static SessionFactory createSessionFactory() {
//        if (sessionFactory == null) {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        }
//        return sessionFactory;
//    }
}
