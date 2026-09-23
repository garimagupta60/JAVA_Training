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
        if (product == null) {
            throw new ProductNotFoundException("Cannot add missing (null) product to order");
        }
        if (product.getAvailableQuantity() == null || product.getAvailableQuantity() <= 0) {
            throw new InsufficientInventoryException("Insufficient inventory for product: " + product.getName() + " (Available: " + product.getAvailableQuantity() + ")");
        }
        products.add(product);
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new InvalidOrderException("Invalid quantity: " + quantity + ". Quantity must be greater than zero.");
        }
        if (product == null) {
            throw new ProductNotFoundException("Cannot add missing (null) product to order");
        }
        if (product.getAvailableQuantity() == null || product.getAvailableQuantity() < quantity) {
            throw new InsufficientInventoryException("Insufficient inventory for product: " + product.getName() 
                    + " (Requested: " + quantity + ", Available: " + product.getAvailableQuantity() + ")");
        }
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product == null) {
            throw new ProductNotFoundException("Cannot remove missing (null) product from order");
        }
        if (!products.contains(product)) {
            throw new ProductNotFoundException("Product with ID " + product.getId() + " is not in the order");
        }
        products.remove(product);
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