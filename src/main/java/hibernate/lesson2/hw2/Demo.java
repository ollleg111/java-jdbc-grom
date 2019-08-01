package hibernate.lesson2.hw2;


import hibernate.lesson2.hw1.Product;
import hibernate.lesson2.hw1.ProductDAO;

import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        Product product1 = new Product();
        product1.setName("!!!");
        product1.setDescription("black1");
        product1.setPrice(1111);

        Product product2 = new Product();
        product2.setName("as I am");
        product2.setDescription("B_L_A_C_K");
        product2.setPrice(5000);

        Product product3 = new Product();
        product3.setName("A_A_A_A_A");
        product3.setDescription("A_A_A_A_A_A_A");
        product3.setPrice(1000);

        try {
//            System.out.println(ProductDAO.save(product3));
//            product3.setName("B_B_B");
//            System.out.println(ProductDAO.update(product3));
//            System.out.println(ProductDAO.delete(product3));

            List<Product> products = Arrays.asList(product1, product2, product3);

            ProductDAO.saveAll(products);
//            ProductDAO.updateAll(products);
//            ProductDAO.deleteAll(products);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
