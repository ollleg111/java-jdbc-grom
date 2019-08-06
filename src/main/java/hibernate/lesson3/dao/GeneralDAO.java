package hibernate.lesson3.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GeneralDAO<T> {

    private static SessionFactory sessionFactory;

    public T save(T t) throws Exception {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("save method was failed");
        }
        System.out.println("Product was saving");
        return t;
    }

    public T update(T t) throws Exception {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.update(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("update method was failed");
        }
        System.out.println("Product was updated");
        return t;
    }

    public void delete(long id) throws Exception {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.delete(id);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("delete method was failed");
        }
        System.out.println("Product was deleted");

    }

    public T findById(long id) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            
            session.find(t);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("find is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("find method was failed");
        }
        System.out.println("Product was find");
        return t;
    }


    public static SessionFactory createSessionFactory() {
        //singleton
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
