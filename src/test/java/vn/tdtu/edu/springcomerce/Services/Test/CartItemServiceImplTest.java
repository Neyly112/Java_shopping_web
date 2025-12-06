package vn.tdtu.edu.springcomerce.Services.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.tdtu.edu.springcomerce.Repository.CartItemRepo;
import vn.tdtu.edu.springcomerce.Services.Impl.CartItemServiceImpl;
import vn.tdtu.edu.springcomerce.models.CartItem;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {

    @Mock
    private CartItemRepo cartItemRepository;

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setQuantity(2);
        cartItem.setPrice(100.0);
    }

    @Test
    public void testGetAllCartItems() {
        // Given
        when(cartItemRepository.findAll()).thenReturn(List.of(cartItem));

        // When
        List<CartItem> cartItems = cartItemService.getAllCartItems();

        // Then
        assertNotNull(cartItems);
        assertEquals(1, cartItems.size());
        verify(cartItemRepository, times(1)).findAll();
    }

    @Test
    public void testGetCartItemById_Success() {
        // Given
        when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));

        // When
        CartItem foundItem = cartItemService.getCartItemById(1L);

        // Then
        assertNotNull(foundItem);
        assertEquals(1L, foundItem.getId());
        verify(cartItemRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCartItemById_NotFound() {
        // Given
        when(cartItemRepository.findById(1L)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(RuntimeException.class, () -> cartItemService.getCartItemById(1L));
    }

    @Test
    public void testSaveCartItem() {
        // Given
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        // When
        CartItem savedItem = cartItemService.saveCartItem(cartItem);

        // Then
        assertNotNull(savedItem);
        assertEquals(1L, savedItem.getId());
        verify(cartItemRepository, times(1)).save(cartItem);
    }

    @Test
    public void testDeleteCartItem() {
        // Given
        doNothing().when(cartItemRepository).deleteById(1L);

        // When
        cartItemService.deleteCartItem(1L);

        // Then
        verify(cartItemRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateCartItemQuantity() {
        // Given
        CartItem existingItem = new CartItem();
        existingItem.setId(1L);
        existingItem.setQuantity(2);
        existingItem.setPrice(100.0);

        when(cartItemRepository.findByProductId(1L)).thenReturn(existingItem);
        when(cartItemRepository.save(existingItem)).thenReturn(existingItem);

        // When
        cartItemService.updateCartItemQuantity(1L, 5);

        // Then
        assertEquals(5, existingItem.getQuantity());
        verify(cartItemRepository, times(1)).save(existingItem);
    }
}
