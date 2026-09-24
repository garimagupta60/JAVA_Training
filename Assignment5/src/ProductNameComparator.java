import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {

    @Override
    public int compare(Product firstProduct, Product secondProduct) {
        return firstProduct.getName()
                .compareTo(secondProduct.getName());
    }
}
