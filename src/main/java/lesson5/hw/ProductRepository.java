package lesson5.hw;
import org.hibernate.Session;

public class ProductRepository {

    public static void save(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        System.out.println("Product was saving");

        session.close();
    }

    public static void delete(long id) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(session.get(Product.class, id));
        session.getTransaction().commit();
        System.out.println("Product was deleting");

        session.close();
    }

    public static void update(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(product);
        session.getTransaction().commit();
        System.out.println("Product was updating");

        session.close();
    }
}
