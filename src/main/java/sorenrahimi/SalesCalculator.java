package sorenrahimi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesCalculator {
    public Map<Customer, Double> calculateTotalSalesForCustomer (List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,
                        Collectors.summingDouble(order -> order.getProducts().stream()
                                .mapToDouble(product -> product.getPrice()).sum())));
    }
}
