package vn.tdtu.edu.springcomerce.Services.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.tdtu.edu.springcomerce.Repository.ProductRepo;
import vn.tdtu.edu.springcomerce.Services.Impl.ProductServiceImpl;
import vn.tdtu.edu.springcomerce.models.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product1", "Category1", "Brand1", "Color1", "100", "Description1", "image1.jpg"),
                new Product(2L, "Product2", "Category2", "Brand2", "Color2", "200", "Description2", "image2.jpg")
        );
        when(productRepo.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals(mockProducts, result);
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testGetProductById_ProductExists() {

        Product mockProduct = new Product(1L, "Product1", "Category1", "Brand1", "Color1", "100", "Description1", "image1.jpg");
        when(productRepo.findById(1L)).thenReturn(Optional.of(mockProduct));
        Product result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals("Product1", result.getName());
        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_ProductDoesNotExist() {

        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        Product result = productService.getProductById(1L);

        assertNull(result);
        verify(productRepo, times(1)).findById(1L);
    }
    @Test
    void testFilterProducts() {

        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Product1", "Electronics", "Brand1", "Red", "100", "Description1", "image1.jpg"),
                new Product(2L, "Product2", "Clothing", "Brand2", "Blue", "200", "Description2", "image2.jpg")
        );
        when(productRepo.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.filterProducts("Electronics", 50.0, 150.0, "Brand1", "Red");

        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getName());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product(1L, "OldProduct", "OldCategory", "OldBrand", "OldColor", "100", "OldDescription", "oldImage.jpg");
        Product updatedProduct = new Product(1L, "NewProduct", "NewCategory", "NewBrand", "NewColor", "150", "NewDescription", "newImage.jpg");
        when(productRepo.findById(1L)).thenReturn(Optional.of(existingProduct));

        productService.update(updatedProduct);

        verify(productRepo, times(1)).save(argThat(product ->
                product.getName().equals("NewProduct") &&
                        product.getCategory().equals("NewCategory") &&
                        product.getPrice().equals("150")
        ));
    }

    @Test
    void testDeleteProductById() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.deleteProductById(productId);

        // Assert
        verify(productRepo, times(1)).deleteById(productId);
    }

}
