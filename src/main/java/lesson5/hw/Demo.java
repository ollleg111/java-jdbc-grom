package lesson5.hw;


public class Demo {
    public static void main(String[] args) {

        Product product1 = new Product();
        product1.setId(100);
        product1.setName("table");
        product1.setDescription("grey and blue");
        product1.setPrice(70);

        Product product2 = new Product();
        product1.setId(101);
        product1.setName("table1");
        product1.setDescription("white and orange");
        product1.setPrice(50);

        ProductRepository.save(product1);
        ProductRepository.save(product2);

        ProductRepository.delete(product1.getId());
        ProductRepository.update(product2);
    }
}
