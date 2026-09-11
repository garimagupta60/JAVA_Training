public class Main {
    public static void main(String[] args) {
        OrderItem item1 = new OrderItem("Laptop", 1200.0, 1);
        OrderItem item2 = new OrderItem("Mouse", 25.0, 2);
        OrderItem[] items = { item1, item2 };

        Order order = new Order(101, items);

        System.out.print("Order Details -> ");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Items:");
        for (OrderItem item : order.getItems()) {
            System.out.println(item.getProductName() + ": " + item.getQuantity() + "*" + item.getPrice());
        }
        System.out.println("Total Amount: $" + order.getTotalAmount());
        System.out.println("Initial Status: " + order.getStatus());

        order.setStatus(OrderStatus.SHIPPED);
        System.out.println("Updated Status: " + order.getStatus());
    }
}
