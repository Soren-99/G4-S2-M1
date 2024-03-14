package sorenrahimi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    }
}
