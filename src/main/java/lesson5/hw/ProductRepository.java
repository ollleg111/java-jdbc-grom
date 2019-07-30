package lesson5.hw;

import org.hibernate.Session;

public class ProductRepository {

    private static Session getSession() {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        return session;
    }

    public static void save(Product product) {
        getSession().save(product);
        getSession().getTransaction().commit();
        System.out.println("Product was saving");

        getSession().close();
    }

    public static void delete(long id) {
//        Product product = getSession().load(Product.class, id);
//        getSession().delete(product);
        getSession().delete(getSession().get(Product.class, id));
        getSession().getTransaction().commit();
        System.out.println("Product was deleting");

        getSession().close();
    }

    public static void update(Product product) {
        getSession().update(product);
        getSession().getTransaction().commit();
        System.out.println("Product was updating");

        getSession().close();
    }
}
