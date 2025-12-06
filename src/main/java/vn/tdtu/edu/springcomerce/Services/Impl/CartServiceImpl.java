package vn.tdtu.edu.springcomerce.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.springcomerce.Services.CartService;
import vn.tdtu.edu.springcomerce.models.Cart;
import vn.tdtu.edu.springcomerce.models.CartItem;
import vn.tdtu.edu.springcomerce.models.Product;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.Repository.CartItemRepo;
import vn.tdtu.edu.springcomerce.Repository.CartRepo;
import vn.tdtu.edu.springcomerce.Repository.ProductRepo;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Cart getCartByUser(User user) {
        return cartRepo.findByUser(user);
    }

    @Override
    public void addToCart(User user, Long productId, int quantity, double price) {
        Cart cart = cartRepo.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepo.save(cart);
        }

        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem cartItem = new CartItem(cart, product, quantity, price);
        cartItemRepo.save(cartItem);
    }

    @Override
    public void removeFromCart(User user, Long productId) {
        Cart cart = cartRepo.findByUser(user);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for user: " + user.getUsername());
        }
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found for productId: " + productId));

        // Xóa CartItem khỏi giỏ hàng
        cart.getItems().remove(cartItem);
        cartItemRepo.delete(cartItem);
    }
    public boolean isCartEmpty(User user) {
        Cart cart = cartRepo.findByUser(user);
        return cart == null || cart.getItems() == null || cart.getItems().isEmpty();
    }
    public void updateQuantity(User user, Long productId, int quantity) {
        Cart cart = cartRepo.findByUser(user);
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cartItem.setQuantity(quantity);
        cartRepo.save(cart);
    }
}
