package hibernate.lesson2;

import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        Product product1 = new Product();
        product1.setName("!!!");
        product1.setDescription("black1");
        product1.setPrice(200);

        Product product2 = new Product();
        product2.setName("!!!!");
        product2.setDescription("black2");
        product2.setPrice(300);

        Product product3 = new Product();
        product3.setName("!!!!!");
        product3.setDescription("black3");
        product3.setPrice(400);

        List<Product> products = Arrays.asList(product1,product2,product3);

        saveProducts(products);

    }

    public static void save(Product product) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Product was saving");
    }

    public static void saveProducts(List<Product> products) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                save(product);
            }

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null)
                tr.rollback();
        }
        System.out.println("Product was saving");
    }

    public static SessionFactory createSessionFactory() {
        //singleton
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
