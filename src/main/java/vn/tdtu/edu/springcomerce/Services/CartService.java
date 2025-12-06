package vn.tdtu.edu.springcomerce.Services;

import vn.tdtu.edu.springcomerce.models.Cart;
import vn.tdtu.edu.springcomerce.models.User;

public interface CartService {
    Cart getCartByUser(User user);
    void addToCart(User user, Long productId, int quantity, double price);
    void removeFromCart(User user, Long productId);
    boolean isCartEmpty(User user);
    void updateQuantity(User user, Long productId, int quantity);
}
