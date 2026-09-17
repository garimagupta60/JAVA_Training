import java.util.HashSet;
import java.util.Set;

public class Order {

    private final int id;
    private final Set<Integer> productIds;

    public Order(int id) {
        this.id = id;
        this.productIds = new HashSet<>();
    }

    public Order(int id, Set<Integer> productIds) {
        this.id = id;
        this.productIds = new HashSet<>(productIds);
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getProductIds() {
        return Set.copyOf(productIds);
    }

    public void addProduct(int productId) {
        productIds.add(productId);
    }

    public void removeProduct(int productId) {
        productIds.remove(productId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productIds=" + productIds +
                '}';
    }
}