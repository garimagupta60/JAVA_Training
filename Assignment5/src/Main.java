import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        testProductRepository(productRepository);
        testOrderRepository(orderRepository, productRepository);
    }

    private static void testProductRepository(ProductRepository productRepo) {

        productRepo.save(
                new Product(101, "Apple iPhone 15", 69999.00, 10));
        productRepo.save(
                new Product(102, "Samsung Galaxy S24", 74999.00, 5));
        productRepo.save(
                new Product(103, "Sony WH-1000XM5 Headphones", 29990.00, 15));
        productRepo.save(
                new Product(104, "Logitech MX Master 3S", 8495.00, 20));
        productRepo.save(
                new Product(105, "Apple AirPods Pro", 24900.00, 8));

        printList("All Products", productRepo.findAll());

        System.out.println("\nFind product with ID 103");
        printProduct(productRepo.findById(103));

        System.out.println("\nFind product with ID 999");
        printProduct(productRepo.findById(999));

        System.out.println("\nUpdate product with ID 104");

        productRepo.update(
                new Product(104, "Logitech MX Master 3S", 7995.00, 20));

        System.out.println("\nDelete product with ID 105");
        productRepo.delete(105);

        printList("Products after update and delete", productRepo.findAll());
        printList("Products Sorted by Name", productRepo.findAllSorted(new ProductNameComparator()));
        printList("Products Sorted by Price", productRepo.findAllSorted(new ProductPriceComparator()));
        printList("Products Sorted by Available Quantity", productRepo.findAllSorted(new ProductQuantityComparator()));
    }

    private static void testOrderRepository(OrderRepository orderRepo, ProductRepository productRepo) {

        Product p101 = productRepo.findById(101);
        Product p102 = productRepo.findById(102);
        Product p103 = productRepo.findById(103);
        Product p104 = productRepo.findById(104);

        Order order1 = new Order(5001);
        order1.addProduct(p101);
        order1.addProduct(p103);
        order1.addProduct(p104);
        order1.addProduct(p101); // Attempt duplicate add

        orderRepo.save(order1);

        Set<Product> products5002 = new HashSet<>();
        products5002.add(p102);
        products5002.add(p103);

        orderRepo.save(new Order(5002, products5002));

        System.out.println("\nAll Orders");
        for (Order order : orderRepo.findAll()) {
            System.out.println(order);
            System.out.println("Calculated Order Price: " + order.calculateOrderPrice());
        }

        System.out.println("\nFind order with ID 5001");
        printOrder(orderRepo.findById(5001));

        System.out.println("\nFind order with ID 9999");
        printOrder(orderRepo.findById(9999));

        System.out.println("\nUpdate order with ID 5002");

        Set<Product> updatedProducts = new HashSet<>();
        updatedProducts.add(p102);
        updatedProducts.add(p103);
        updatedProducts.add(p104);

        orderRepo.update(new Order(5002, updatedProducts));

        System.out.println("\nDelete order with ID 5001");
        orderRepo.delete(5001);

        System.out.println("\nOrders after update and delete");
        for (Order order : orderRepo.findAll()) {
            System.out.println(order);
            System.out.println("Calculated Order Price: " + order.calculateOrderPrice());
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
            System.out.println("Calculated Order Price: " + order.calculateOrderPrice());
        } else {
            System.out.println("Order not found");
        }
    }

    public static <T> void printList(String title, Collection<T> items) {
        System.out.println("\n" + title);
        if (items == null || items.isEmpty()) {
            System.out.println("No items found");
            return;
        }
        for (T item : items) {
            System.out.println(item);
        }
    }
}