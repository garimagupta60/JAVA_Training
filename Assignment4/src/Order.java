import java.util.HashSet;
import java.util.Set;

public class Order {

    private final Integer id;
    private final Set<Product> products;

    public Order(Integer id) {
        this.id = id;
        this.products = new HashSet<>();
    }

    public Order(Integer id, Set<Product> products) {
        this.id = id;
        this.products = products != null ? new HashSet<>(products) : new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public Set<Product> getProducts() {
        return Set.copyOf(products);
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        if (product != null) {
            products.remove(product);
        }
    }

    public Double calculateOrderPrice() {
        double total = 0.0;
        for (Product product : products) {
            if (product != null && product.getPrice() != null) {
                total += product.getPrice();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", totalPrice=" + calculateOrderPrice() +
                '}';
    }
}