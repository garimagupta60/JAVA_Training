import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<>();

    public Order findById(Integer id) {
        if (id == null || !orders.containsKey(id)) {
            throw new OrderNotFoundException("Order with ID " + id + " not found");
        }
        return orders.get(id);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
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

        // Deduct inventory for each product in the order
        for (Product product : order.getProducts()) {
            product.setAvailableQuantity(product.getAvailableQuantity() - 1);
        }

        orders.put(order.getId(), order);
    }

    public void update(Order order) {
        if (order == null || order.getId() == null || !orders.containsKey(order.getId())) {
            throw new OrderNotFoundException("Cannot update. Order with ID " + (order != null ? order.getId() : "null") + " not found");
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
