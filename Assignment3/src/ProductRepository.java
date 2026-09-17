import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private final Map<Integer, Product> products = new HashMap<>();

    public Product findById(int id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public void save(Product product) {

        if (product == null) {
            return;
        }

        if (products.containsKey(product.getId())) {
            System.out.println("Product with ID " + product.getId() + " already exists");
            return;
        }

        products.put(product.getId(), product);

    }

    public void update(Product product) {
        if (product != null && products.containsKey(product.getId())) {
            products.put(product.getId(), product);
        }
    }

    public void delete(int id) {
        products.remove(id);
    }
}
