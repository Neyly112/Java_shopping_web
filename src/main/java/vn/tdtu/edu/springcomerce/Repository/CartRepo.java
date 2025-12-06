package vn.tdtu.edu.springcomerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tdtu.edu.springcomerce.models.Cart;
import vn.tdtu.edu.springcomerce.models.User;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);

}
