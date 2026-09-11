public class Order {
    private final int orderId;
    private OrderItem[] items;
    private OrderStatus status;

    public Order(int orderId, OrderItem[] items) {
        this.orderId = orderId;
        this.items = items;
        this.status = OrderStatus.PENDING;
    }

    public int getOrderId() {
        return orderId;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
