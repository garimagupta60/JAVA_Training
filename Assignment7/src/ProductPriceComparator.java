import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product firstProduct, Product secondProduct) {
        return Double.compare(
                firstProduct.getPrice(),
                secondProduct.getPrice());

    }
}
