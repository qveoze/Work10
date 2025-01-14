package work10;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(1, "Юля", LocalDate.of(2024, 6, 7), "PENDING", 150),
                new Order(2, "Анна", LocalDate.of(2024, 6, 10), "DELIVERED", 1899),
                new Order(3, "Саша", LocalDate.of(2024, 6, 14), "SHIPPED", 2699),
                new Order(4, "Иван", LocalDate.of(2024, 6, 19), "PENDING", 1099),
                new Order(5, "София", LocalDate.of(2024, 6, 25), "SHIPPED", 500),
                new Order(6, "Андрей", LocalDate.of(2024, 6, 27), "SHIPPED", 250)
        );
        LocalDate filterDate = LocalDate.of(2024, 6, 10);
        List<Order> dateSort = orders.stream()
                .filter(order -> order.getOrderDate().isAfter(filterDate))
                .collect(Collectors.toList());
        System.out.println("Заказы после: " + filterDate);
        dateSort.forEach(System.out::println);
        System.out.println();

        List<Order> shippedOrders = orders.stream()
                .filter(order -> order.getStatus().equals("SHIPPED"))
                .collect(Collectors.toList());
        System.out.println("Заказы со статусом 'SHIPPED': ");
        shippedOrders.forEach(System.out::println);
        System.out.println();

        Map<String, Long> statusCounts = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        System.out.println("Количество заказов по статусам: ");
        statusCounts.forEach((status, count) -> System.out.println(status + ": " + count));
        System.out.println();

        List<Order> AmountOrders = orders.stream()
                .filter(order -> order.getAmount() > 500)
                .collect(Collectors.toList());
        System.out.println("Заказы стоимостью более 500: ");
        AmountOrders.forEach(System.out::println);
        System.out.println();

        Optional<Order> maxOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::getAmount));
        maxOrder.ifPresent(order -> System.out.println("Клиент с наибольшим заказом: " + order.getCustomerName()));
        maxOrder.ifPresent(order -> System.out.println("Наибольшая сумма заказа: " + order.getAmount()));
    }
}


