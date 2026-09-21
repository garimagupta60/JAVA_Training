import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<>();

    public Order findById(Integer id) {
        if (id == null) {
            return null;
        }
        return orders.get(id);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public void save(Order order) {

        if (order == null || order.getId() == null) {
            return;
        }

        if (orders.containsKey(order.getId())) {
            System.out.println("Order with ID " + order.getId() + " already exists");
            return;
        }

        orders.put(order.getId(), order);
    }

    public void update(Order order) {
        if (order != null && order.getId() != null && orders.containsKey(order.getId())) {
            orders.put(order.getId(), order);
        }
    }

    public void delete(Integer id) {
        if (id != null) {
            orders.remove(id);
        }
    }
}
