import java.util.Comparator;

public class ProductQuantityComparator implements Comparator<Product> {

    @Override
    public int compare(Product firstProduct, Product secondProduct) {
        return Integer.compare(
                firstProduct.getAvailableQuantity(),
                secondProduct.getAvailableQuantity());
    }
}
