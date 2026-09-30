import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {

    private final Map<Integer, Product> products = new HashMap<>();

    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(products.get(id));
    }


    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> findAllSorted(Comparator<Product> comparator) {
        return products.values()
                .stream()
                .sorted(comparator)
                .toList();
    }

    public List<String> getProductNames() {
        return products.values()
                .stream()
                .map(Product::getName)
                .toList();
    }

    public List<Product> getProductsBelowStockThreshold(int threshold) {
        return products.values()
                .stream()
                .filter(product ->
                        product.getAvailableQuantity() < threshold)
                .toList();
    }

    public void save(Product product) {

        if (product == null || product.getId() == null) {
            return;
        }

        if (products.containsKey(product.getId())) {
            System.out.println("Product with ID " + product.getId() + " already exists");
            return;
        }

        products.put(product.getId(), product);

    }

    public void update(Product product) {
        if (product == null || product.getId() == null || !products.containsKey(product.getId())) {
            throw new ProductNotFoundException("Cannot update. Product with ID " + (product != null ? product.getId() : "null") + " not found");
        }
        products.put(product.getId(), product);
    }

    public void delete(Integer id) {
        if (id == null || !products.containsKey(id)) {
            throw new ProductNotFoundException("Cannot delete. Product with ID " + id + " not found");
        }
        products.remove(id);
    }
}
