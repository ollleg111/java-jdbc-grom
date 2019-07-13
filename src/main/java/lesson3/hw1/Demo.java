package lesson3.hw1;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        System.out.println(productDAO.findProductsByPrice(200, 100));
        System.out.println(productDAO.findProductsWithEmptyDescription());
        try {
            System.out.println(productDAO.findProductsByName("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
