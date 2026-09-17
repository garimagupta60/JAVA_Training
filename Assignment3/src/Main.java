import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        testProductRepository(productRepository);
        testOrderRepository(orderRepository);
    }

    private static void testProductRepository(ProductRepository productRepo) {

        productRepo.save(new Product(101, "Apple iPhone 15", 69999.00));
        productRepo.save(new Product(102, "Samsung Galaxy S24", 74999.00));
        productRepo.save(new Product(103, "Sony WH-1000XM5 Headphones", 29990.00));
        productRepo.save(new Product(104, "Logitech MX Master 3S", 8495.00));
        productRepo.save(new Product(105, "Apple AirPods Pro", 24900.00));

        System.out.println("\nAll Products");
        for (Product product : productRepo.findAll()) {
            System.out.println(product);
        }

        System.out.println("\nFind product with ID 103");
        printProduct(productRepo.findById(103));

        System.out.println("\nFind product with ID 999");
        printProduct(productRepo.findById(999));

        System.out.println("\nUpdate product with ID 104");

        productRepo.update(
                new Product(104, "Logitech MX Master 3S", 7995.00));

        System.out.println("\nDelete product with ID 105");
        productRepo.delete(105);

        System.out.println("\nProducts after update and delete");
        for (Product product : productRepo.findAll()) {
            System.out.println(product);
        }
    }

    private static void testOrderRepository(OrderRepository orderRepo) {

        Order order1 = new Order(5001);
        order1.addProduct(101);
        order1.addProduct(103);
        order1.addProduct(104);
        order1.addProduct(101);

        orderRepo.save(order1);

        Set<Integer> productIds = new HashSet<>();
        productIds.add(102);
        productIds.add(105);

        orderRepo.save(new Order(5002, productIds));

        System.out.println("\nAll Orders");
        for (Order order : orderRepo.findAll()) {
            System.out.println(order);
        }

        System.out.println("\nFind order with ID 5001");
        printOrder(orderRepo.findById(5001));

        System.out.println("\nFind order with ID 9999");
        printOrder(orderRepo.findById(9999));

        System.out.println("\nUpdate order with ID 5002");

        Set<Integer> updatedProductIds = new HashSet<>();
        updatedProductIds.add(102);
        updatedProductIds.add(103);
        updatedProductIds.add(104);

        orderRepo.update(new Order(5002, updatedProductIds));

        System.out.println("\nDelete order with ID 5001");
        orderRepo.delete(5001);

        System.out.println("\nOrders after update and delete");
        for (Order order : orderRepo.findAll()) {
            System.out.println(order);
        }
    }

    private static void printProduct(Product product) {
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found");
        }
    }

    private static void printOrder(Order order) {
        if (order != null) {
            System.out.println(order);
        } else {
            System.out.println("Order not found");
        }
    }
}