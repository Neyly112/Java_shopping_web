package vn.tdtu.edu.springcomerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tdtu.edu.springcomerce.models.Order;
import vn.tdtu.edu.springcomerce.models.User;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    Order findByIdAndUser(Long id, User user);
}
