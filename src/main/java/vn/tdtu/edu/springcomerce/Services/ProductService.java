package vn.tdtu.edu.springcomerce.Services;

import org.springframework.web.multipart.MultipartFile;
import vn.tdtu.edu.springcomerce.models.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void save(Product product);
    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<String> getAllCategories();

    List<String> getAllBrands();

    List<String> getAllColors();

    List<String> getAllPrices();

    List<Product> filterProducts(String category, Double minPrice, Double maxPrice, String brand, String color);

    List<Product> searchProducts(String keyword);

    void deleteProductById(Long id);

    void update(Product product);
}
