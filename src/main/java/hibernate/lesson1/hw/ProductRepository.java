package hibernate.lesson1.hw;

import hibernate.lesson1.HibernateUtils;
import hibernate.lesson1.Product;
import org.hibernate.Session;

public class ProductRepository {

    public static void save(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        System.out.println("ProductOne was saving");

        session.close();
    }

    public static void delete(long id) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(session.get(Product.class, id));
        session.getTransaction().commit();
        System.out.println("ProductOne was deleting");

        session.close();
    }

    public static void update(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(product);
        session.getTransaction().commit();
        System.out.println("ProductOne was updating");

        session.close();
    }
}
