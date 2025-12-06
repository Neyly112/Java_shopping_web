package vn.tdtu.edu.springcomerce.Services.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import vn.tdtu.edu.springcomerce.Services.Impl.CartServiceImpl;
import vn.tdtu.edu.springcomerce.models.Cart;
import vn.tdtu.edu.springcomerce.models.CartItem;
import vn.tdtu.edu.springcomerce.models.Product;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.Repository.CartItemRepo;
import vn.tdtu.edu.springcomerce.Repository.CartRepo;
import vn.tdtu.edu.springcomerce.Repository.ProductRepo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {

    @Mock
    private CartRepo cartRepo;

    @Mock
    private CartItemRepo cartItemRepo;

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private CartServiceImpl cartService;

    private User user;
    private Product product;
    private Cart cart;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUsername("testUser");
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setCategory("Electronics");
        product.setBrand("TestBrand");
        product.setColor("Black");
        product.setPrice("199.99");
        product.setDescription("Test product description");
        product.setImage("test_image_path");
        cart = new Cart();
        cart.setUser(user);
        cartItem = new CartItem(cart, product, 1, 199.99);
    }

    @Test
    void addToCart() {
        when(cartRepo.findByUser(user)).thenReturn(cart);
        when(productRepo.findById(1L)).thenReturn(java.util.Optional.of(product));
        cartService.addToCart(user, 1L, 2, 199.99);
        verify(cartRepo, times(1)).findByUser(user);
        verify(cartItemRepo, times(1)).save(any(CartItem.class));
        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }


    @Test
    void removeFromCart() {
        // Arrange
        when(cartRepo.findByUser(user)).thenReturn(cart);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cartService.removeFromCart(user, 1L));
    }


    @Test
    void updateQuantity() {
        when(cartRepo.findByUser(user)).thenReturn(cart);
        assertThrows(RuntimeException.class, () -> cartService.updateQuantity(user, 1L, 5));
    }

    @Test
    void isCartEmpty() {
        when(cartRepo.findByUser(user)).thenReturn(cart);
        boolean result = cartService.isCartEmpty(user);
        assertTrue(result);
    }

}
