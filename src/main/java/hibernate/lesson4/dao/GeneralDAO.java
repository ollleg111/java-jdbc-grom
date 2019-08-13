package hibernate.lesson4.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GeneralDAO<T> {

    private Class<T> typeParameterClass;
    private static SessionFactory sessionFactory;

    public void setTypeParameterClass(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

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
            throw new Exception("save method was failed in class" + typeParameterClass.getName());
        }
        System.out.println("Product " + t.getClass().getName() + " was saving");
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
            System.err.println("update is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("update method was failed in class" + typeParameterClass.getName());
        }
        System.out.println("Product " + t.getClass().getName() + " updated");
        return t;
    }

    public void delete(long id) throws Exception {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(typeParameterClass, id));

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
            throw new Exception("delete method was failed in class" + typeParameterClass.getName());
        }
        System.out.println("Product with id:" + id + " was deleted");

    }

    public T findById(long id) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            return session.get(typeParameterClass, id);

        } catch (HibernateException e) {
            throw new Exception("operation with id: " + id + " was filed in class " + typeParameterClass.getName());
        }
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
