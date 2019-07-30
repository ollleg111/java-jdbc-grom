package lesson5.hw;

import lesson5.Product;

public class Demo {
    public static void main(String[] args) {

        Product product1 = new Product();
        product1.setId(131);
        product1.setName("table");
        product1.setDescription("grey");
        product1.setPrice(100);

        Product product2 = new Product();
        product2.setId(171);
        product2.setName("table1");
        product2.setDescription("white and orange");
        product2.setPrice(50);

//        ProductRepository.save(product1);
//        ProductRepository.save(product2);
//        ProductRepository.delete(product2.getId());
        ProductRepository.update(product1);
    }
}
