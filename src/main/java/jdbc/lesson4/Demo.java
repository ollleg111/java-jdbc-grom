package jdbc.lesson4;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        Product product1 = new Product(250, "!!!","!!!", 9999);
        Product product2 = new Product(251, "!!!","!!!", 9999);
        Product product3 = new Product(252, "!!!","!!!", 9999);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        TransactionDemo.save(productList);
    }
}
