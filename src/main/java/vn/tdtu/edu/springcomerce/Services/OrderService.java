package vn.tdtu.edu.springcomerce.Services;

import vn.tdtu.edu.springcomerce.models.Order;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.models.OrderItem;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> findOrdersByUser(User user);
    List<OrderItem> getOrderItemsFromCart(User user);
    void saveOrder(Order order);
    Order findOrderByIdAndUser(Long id, User user);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
    void acceptOrder(Long id);
}
