import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<>();

    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return orders.values()
                .stream()
                .filter(order -> order.getCustomer().equals(customer))
                .toList();
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orders.values()
                .stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }

    public Map<OrderStatus, Double> getOrderTotalSumByStatus() {
        return orders.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.summingDouble(Order::calculateOrderTotal)
                ));
    }

    public Map<Product, Long> getProductOrderCount() {
        return orders.values()
                .stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(
                        product -> product,
                        Collectors.counting()
                ));
    }

    public Optional<Map.Entry<Product, Long>> getTopSellingProduct() {
        return getProductOrderCount()
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }

    public void save(Order order) {

        if (order == null || order.getId() == null) {
            throw new InvalidOrderException("Order and Order ID cannot be null");
        }

        if (orders.containsKey(order.getId())) {
            System.out.println("Order with ID " + order.getId() + " already exists");
            return;
        }

        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new InvalidOrderException("Cannot place an empty order. Order ID: " + order.getId());
        }

        for (Product product : order.getProducts()) {
            if (product == null) {
                throw new ProductNotFoundException("Order contains a missing (null) product");
            }
            if (product.getAvailableQuantity() == null || product.getAvailableQuantity() < 0) {
                throw new InvalidOrderException("Invalid quantity for product: " + product.getName());
            }
            if (product.getAvailableQuantity() <= 0) {
                throw new InsufficientInventoryException("Insufficient inventory for product: " + product.getName()
                        + " (Available: " + product.getAvailableQuantity() + ")");
            }
        }

        for (Product product : order.getProducts()) {
            product.setAvailableQuantity(product.getAvailableQuantity() - 1);
        }

        orders.put(order.getId(), order);
    }

    public void update(Order order) {
        if (order == null || order.getId() == null || !orders.containsKey(order.getId())) {
            throw new OrderNotFoundException(
                    "Cannot update. Order with ID " + (order != null ? order.getId() : "null") + " not found");
        }
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new InvalidOrderException("Cannot update order to be empty (Order ID: " + order.getId() + ")");
        }
        orders.put(order.getId(), order);
    }

    public void delete(Integer id) {
        if (id == null || !orders.containsKey(id)) {
            throw new OrderNotFoundException("Cannot delete. Order with ID " + id + " not found");
        }
        orders.remove(id);
    }
}
