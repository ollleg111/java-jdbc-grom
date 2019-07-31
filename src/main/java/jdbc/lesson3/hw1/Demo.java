package jdbc.lesson3.hw1;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        try {
            System.out.println(productDAO.findProductsByPrice(200, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(productDAO.findProductsWithEmptyDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(productDAO.findProductsByName("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
