package vn.tdtu.edu.springcomerce.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.springcomerce.Repository.CartItemRepo;
import vn.tdtu.edu.springcomerce.Services.CartItemService;
import vn.tdtu.edu.springcomerce.models.CartItem;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepo cartItemRepository;

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void updateCartItemQuantity(Long itemId, int quantity) {
        CartItem cartItem = cartItemRepository.findByProductId(itemId);
        System.out.println("Updating CartItem: " + cartItem);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        System.out.println("CartItem updated: " + cartItem);
    }
}
