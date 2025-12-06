package vn.tdtu.edu.springcomerce.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.springcomerce.Repository.CartRepo;
import vn.tdtu.edu.springcomerce.Repository.OrderItemRepo;
import vn.tdtu.edu.springcomerce.Repository.ProductRepo;
import vn.tdtu.edu.springcomerce.Services.OrderService;
import vn.tdtu.edu.springcomerce.models.*;
import vn.tdtu.edu.springcomerce.Repository.OrderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> findOrdersByUser(User user) {
        return orderRepo.findByUser(user);
    }

    @Override
    public Order findOrderByIdAndUser(Long id, User user) {
        return orderRepo.findByIdAndUser(id, user);
    }

    @Override
    public List<OrderItem> getOrderItemsFromCart(User user) {
        Cart cart = cartRepo.findByUser(user);
        List<OrderItem> orderItems = new ArrayList<>();
        if (cart != null) {
            for (CartItem cartItem : cart.getItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(Double.parseDouble(cartItem.getProduct().getPrice()));
                orderItems.add(orderItem);
            }
        }
        return orderItems;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepo.save(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItemRepo.save(orderItem);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }


    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new RuntimeException("Order not found");
        }
    }
    public void acceptOrder(Long id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus("Accepted");
            orderRepo.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}
