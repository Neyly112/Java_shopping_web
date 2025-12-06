package vn.tdtu.edu.springcomerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.tdtu.edu.springcomerce.models.Cart;
import vn.tdtu.edu.springcomerce.models.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductId(Cart cart, Long productId);
    @Query("SELECT c FROM CartItem c WHERE c.product.id = :itemId ORDER BY c.cart.id DESC LIMIT 1")
    CartItem findByProductId(Long itemId);

}
