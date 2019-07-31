package jdbc.lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(10, "test", "test description", 99);
        //productDAO.save(product);

        try{
            System.out.println(productDAO.getProducts());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            productDAO.delete(1001);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            productDAO.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
