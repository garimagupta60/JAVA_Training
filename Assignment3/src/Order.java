import java.util.HashSet;
import java.util.Set;

public class Order {

    private final Integer id;
    private final Set<Integer> productIds;

    public Order(Integer id) {
        this.id = id;
        this.productIds = new HashSet<>();
    }

    public Order(Integer id, Set<Integer> productIds) {
        this.id = id;
        this.productIds = productIds != null ? new HashSet<>(productIds) : new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public Set<Integer> getProductIds() {
        return Set.copyOf(productIds);
    }

    public void addProduct(Integer productId) {
        if (productId != null) {
            productIds.add(productId);
        }
    }

    public void removeProduct(Integer productId) {
        if (productId != null) {
            productIds.remove(productId);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productIds=" + productIds +
                '}';
    }
}