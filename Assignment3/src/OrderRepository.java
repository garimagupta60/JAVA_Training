import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<>();

    public Order findById(int id) {
        return orders.get(id);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public void save(Order order) {

        if (orders.containsKey(order.getId())) {
            System.out.println("Order with ID " + order.getId() + " already exists");
        }

        if (order != null) {
            orders.put(order.getId(), order);
        }
    }

    public void update(Order order) {
        if (order != null && orders.containsKey(order.getId())) {
            orders.put(order.getId(), order);
        }
    }

    public void delete(int id) {
        orders.remove(id);
    }
}
