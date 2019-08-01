package hibernate.lesson2.hw2;

public class Demo {

    public static void main(String[] args) {

        try {
            System.out.println(ProductDAO.findById((long) 31));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ProductDAO.findByName("+++"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ProductDAO.findByContainedName("+"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ProductDAO.findByPrice(500,200));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ProductDAO.findByNameSortedAsc("!%"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ProductDAO.findByNameSortedDesc("!%"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println(ProductDAO.findByPriceSortedDesc(500,200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
