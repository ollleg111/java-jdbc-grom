package hibernate.lesson4.dao;

import hibernate.lesson4.constants.Constants;
import hibernate.lesson4.model.User;
import hibernate.lesson4.controller.SessionAuthorization;
import hibernate.lesson4.model.UserType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO extends GeneralDAO<User> {

    public UserDAO() {
        setTypeParameterClass(User.class);
    }

    public static boolean isAdmin() {
        return (SessionAuthorization.getAuthorized() != null &&
                SessionAuthorization.getAuthorized().getUserType() == UserType.ADMIN);
    }

    public User login(String userName, String password) throws Exception {

        try (Session session = createSessionFactory().openSession()) {
            Query<User> query = session.createNativeQuery(Constants.FIND_USER_BY_NAME_AND_PASS, User.class);
            query.setParameter(1, userName);
            query.setParameter(2, password);

            return query.getSingleResult();

        } catch (HibernateException e) {
            throw new Exception("login(String userName, String password) method was failed from class " +
                    UserDAO.class.getName());
        }
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
