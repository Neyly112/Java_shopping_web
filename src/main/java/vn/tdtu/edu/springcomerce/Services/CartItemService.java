package vn.tdtu.edu.springcomerce.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.springcomerce.Repository.CartItemRepo;
import vn.tdtu.edu.springcomerce.models.CartItem;

import java.util.List;

@Service
public interface CartItemService {
    public List<CartItem> getAllCartItems();
    public CartItem getCartItemById(Long id);
    public CartItem saveCartItem(CartItem cartItem);
    public void deleteCartItem(Long id);
    void updateCartItemQuantity(Long id, int quantity);

}
