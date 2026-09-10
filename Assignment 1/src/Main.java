public class Main {
    public static void main(String[] args) {
        Object customer = new Customer(1, "Garima", "garima@gmail.com");
        System.out.println(customer);

        Product product = new Product(101, "Mobile", 55000);
        System.out.println(product);
    }
}
