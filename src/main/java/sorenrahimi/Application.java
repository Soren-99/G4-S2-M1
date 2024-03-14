package sorenrahimi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args){

        Customer customer1 = new Customer(1L, "Marco Bianchi", 1);
        Customer customer2 = new Customer(2L, "Luca Verdi", 2);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, "Consegnato", LocalDate.now(), LocalDate.now(), new ArrayList<>(), customer1));
        orders.add(new Order(2L, "In consegna", LocalDate.now(), LocalDate.now(), new ArrayList<>(), customer1));
        orders.add(new Order(3L, "Consegnato", LocalDate.now(), LocalDate.now(), new ArrayList<>(), customer2));

        Map<Customer, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        for (Map.Entry<Customer, List<Order>> entry : ordersByCustomer.entrySet()) {
            Customer customer = entry.getKey();
            List<Order> customerOrders = entry.getValue();

            System.out.println("Cliente: " + customer.getName());
            System.out.println("Ordini:");
            for (Order order : customerOrders){
                System.out.println(" - ID ordine: " + order.getId() + ", Stato: " + order.getStatus());
            }
            System.out.println();
        }

        Product product1 = new Product(1L, "Prodotto1", "Categoria1", 12.0);
        Product product2 = new Product(2L, "Prodotto2", "Categoria2", 27.0);
        Product product3 = new Product(3L, "Prodotto3", "Categoria1", 8.0);

        Order order1 = new Order(1L, "Consegnato", LocalDate.now(), LocalDate.now(), List.of(product1, product2), customer1);
        Order order2 = new Order(2L, "In transito", LocalDate.now(), LocalDate.now(), List.of(product3), customer2);
        Order order3 = new Order(1L, "Consegnato", LocalDate.now(), LocalDate.now(), List.of(product1, product3), customer1);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        SalesCalculator salesCalculator = new SalesCalculator();
        salesCalculator.calculateTotalSalesForCustomer(orders).forEach((customer, totalSales) -> {
            System.out.println("Customer: " + customer.getName() + ", Totale vendite " + totalSales);
        });

        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "laptop", "elettronica", 989.99));
        products.add(new Product(2L, "cellulare", "elettronica", 389.99));
        products.add(new Product(3L, "televisore", "elettronica", 1189.99));
        products.add(new Product(4L, "cuffie", "accessori", 19.99));

        ExpensiveProductFinder finder = new ExpensiveProductFinder();
        Optional<Product> mostExpensiveProduct = finder.findMostExpensiveProduct(products);
        mostExpensiveProduct.ifPresent(product -> {
            System.out.println("Prodotto più costoso: " + product.getName() + " - Price: " + product.getPrice());
        });



    }
}
