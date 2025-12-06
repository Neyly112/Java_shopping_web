package vn.tdtu.edu.springcomerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tdtu.edu.springcomerce.models.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}