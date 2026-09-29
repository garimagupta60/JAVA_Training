import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private final Map<Integer, Product> products = new HashMap<>();

    public Product findById(Integer id) {
        if (id == null || !products.containsKey(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        return products.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> findAllSorted(Comparator<Product> comparator) {

        List<Product> sortedProducts =
                new ArrayList<>(products.values());

        sortedProducts.sort(comparator);

        return sortedProducts;
    }

    public void save(Product product) {

        if (product == null || product.getId() == null) {
            return;
        }

        if (products.containsKey(product.getId())) {
            logger.warn("Product with ID {} already exists", product.getId());
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
