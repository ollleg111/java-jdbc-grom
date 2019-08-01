package hibernate.lesson2.hw2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    /*
    https://www.codeflow.site/ru/article/hibernate__hibernate-query-examples-hql
    */

    private static final String FIND_BY_ID                  = "from Product where ID = :id";
    private static final String FIND_BY_NAME                = "from Product where NAME = :name";
    private static final String FIND_BY_CONTAINED_NAME      = "from Product where NAME like :name";
    private static final String FIND_BY_PRICE               = "from Product where PRICE between :min and :max";
    private static final String FIND_BY_NAME_SORTED_ASC     = "from Product where NAME like :name order by NAME asc";
    private static final String FIND_BY_NAME_SORTED_DESC    = "from Product where NAME like :name order by NAME desc";
    private static final String FIND_BY_PRICE_SORTED_DESC   = "from Product where PRICE between :min and :max order by PRICE desc";

    /*
    findById(Long id) - поиск продукта по id
    */
    public static Product findById(Long id) throws Exception {

        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_ID, Product.class);
            query.setParameter("id", id);

            return query.getSingleResult();

        } catch (HibernateException e) {
            throw new Exception("findById method was failed");
        }
    }

    /*
    findByName(String name) - поиск продуктов по имени
    */
    public static List<Product> findByName(String name) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_NAME, Product.class);
            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findByName method was failed");
        }
    }

    /*
    findByContainedName(String name) - поиск продуктов, которые в своем имени содержать слово name
    */
    public static List<Product> findByContainedName(String name) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_CONTAINED_NAME, Product.class);
            query.setParameter("name", "%" + name + "%");

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findByContainedName method was failed");
        }
    }

    /*
    findByPrice(int price, int delta) - поиск продуктов по вилке цен price+-delta включительно
    */
    public static List<Product> findByPrice(int price, int delta) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_PRICE, Product.class);
            query.setParameter("min", price - delta);
            query.setParameter("max", price + delta);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findByPrice method was failed");
        }
    }

    /*
    findByNameSortedAsc(String name) - поиск продуктов по имени, результат отсортирован по алфавитному
    порядку колонки name
    */
    public static List<Product> findByNameSortedAsc(String name) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_NAME_SORTED_ASC, Product.class);
            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findByNameSortedAsc method was failed");
        }
    }

    /*
    findByNameSortedDesc - поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name
    */
    public static List<Product> findByNameSortedDesc(String name) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_NAME_SORTED_DESC, Product.class);
            query.setParameter("name", name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findByNameSortedDesc method was failed");
        }
    }

    /*
    findByPriceSortedDesc(int price, int delta) - поиск продуктов по вилке цен price+-delta включительно,
    результат отсортирован по убыванию цен
    */
    public static List<Product> findByPriceSortedDesc(int price, int delta) throws Exception {
        try (Session session = createSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(FIND_BY_PRICE_SORTED_DESC, Product.class);
            query.setParameter("min", price - delta);
            query.setParameter("max", price + delta);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("findByPriceSortedDesc method was failed");
        }
    }

    public static SessionFactory createSessionFactory() {
        //singleton
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
