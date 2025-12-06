package vn.tdtu.edu.springcomerce.Services.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.tdtu.edu.springcomerce.Repository.CartRepo;
import vn.tdtu.edu.springcomerce.Repository.OrderItemRepo;
import vn.tdtu.edu.springcomerce.Repository.OrderRepo;
import vn.tdtu.edu.springcomerce.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private CartRepo cartRepo;

    @Mock
    private OrderItemRepo orderItemRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders_ShouldReturnAllOrders() {
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1L, new User(), new ArrayList<>(), "Pending"));
        mockOrders.add(new Order(2L, new User(), new ArrayList<>(), "Accepted"));
        when(orderRepo.findAll()).thenReturn(mockOrders);
        List<Order> result = orderService.getAllOrders();
        assertEquals(2, result.size());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void findOrdersByUser_ShouldReturnUserOrders() {
        User user = new User();
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1L, user, new ArrayList<>(), "Pending"));
        when(orderRepo.findByUser(user)).thenReturn(mockOrders);
        List<Order> result = orderService.findOrdersByUser(user);
        assertEquals(1, result.size());
        assertEquals("Pending", result.get(0).getStatus());
        verify(orderRepo, times(1)).findByUser(user);
    }

    @Test
    void findOrderByIdAndUser_ShouldReturnOrder() {
        User user = new User();
        Order mockOrder = new Order(1L, user, new ArrayList<>(), "Pending");
        when(orderRepo.findByIdAndUser(1L, user)).thenReturn(mockOrder);
        Order result = orderService.findOrderByIdAndUser(1L, user);
        assertNotNull(result);
        assertEquals("Pending", result.getStatus());
        verify(orderRepo, times(1)).findByIdAndUser(1L, user);
    }

    @Test
    void saveOrder_ShouldSaveOrderAndOrderItems() {
        Order mockOrder = new Order(1L, new User(), new ArrayList<>(), "Pending");
        mockOrder.setOrderItems(new ArrayList<>());
        OrderItem mockItem = new OrderItem();
        mockItem.setPrice(100.0);
        mockItem.setQuantity(1);
        mockOrder.getOrderItems().add(mockItem);
        orderService.saveOrder(mockOrder);
        verify(orderRepo, times(1)).save(mockOrder);
        verify(orderItemRepo, times(1)).save(mockItem);
    }

    @Test
    void deleteOrder_ShouldDeleteOrderById() {
        orderService.deleteOrder(1L);
        verify(orderRepo, times(1)).deleteById(1L);
    }

    @Test
    void getOrderById_ShouldReturnOrderWhenFound() {
        Order mockOrder = new Order(1L, new User(), new ArrayList<>(), "Pending");
        when(orderRepo.findById(1L)).thenReturn(Optional.of(mockOrder));
        Order result = orderService.getOrderById(1L);
        assertNotNull(result);
        assertEquals("Pending", result.getStatus());
        verify(orderRepo, times(1)).findById(1L);
    }

    @Test
    void getOrderById_ShouldThrowExceptionWhenNotFound() {
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.getOrderById(1L));
        assertEquals("Order not found", exception.getMessage());
        verify(orderRepo, times(1)).findById(1L);
    }

    @Test
    void acceptOrder_ShouldUpdateOrderStatusToAccepted() {
        Order mockOrder = new Order(1L, new User(), new ArrayList<>(), "Pending");
        when(orderRepo.findById(1L)).thenReturn(Optional.of(mockOrder));
        orderService.acceptOrder(1L);
        assertEquals("Accepted", mockOrder.getStatus());
        verify(orderRepo, times(1)).save(mockOrder);
    }

    @Test
    void acceptOrder_ShouldThrowExceptionWhenOrderNotFound() {
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.acceptOrder(1L));
        assertEquals("Order not found", exception.getMessage());
        verify(orderRepo, times(1)).findById(1L);
    }
}
