package sorenrahimi;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ExpensiveProductFinder {

    public Optional<Product> findMostExpensiveProduct(List<Product> products){
        return products.stream()
                .max(Comparator.comparing(Product::getPrice));
    }
}
