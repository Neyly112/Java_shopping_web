package vn.tdtu.edu.springcomerce.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.tdtu.edu.springcomerce.Services.ProductService;
import vn.tdtu.edu.springcomerce.models.Product;
import vn.tdtu.edu.springcomerce.Repository.ProductRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<String> getAllCategories() {
        return productRepo.findAll().stream().map(Product::getCategory).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBrands() {
        return productRepo.findAll().stream().map(Product::getBrand).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getAllColors() {
        return productRepo.findAll().stream().map(Product::getColor).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getAllPrices() {
        return productRepo.findAll().stream().map(Product::getPrice).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Product> filterProducts(String category, Double minPrice, Double maxPrice, String brand, String color) {
        List<Product> products = productRepo.findAll();
        if (category != null && !category.isEmpty()) {
            products = products.stream().filter(product -> category.equals(product.getCategory())).collect(Collectors.toList());
        }
        if (minPrice != null && maxPrice != null) {
            products = products.stream().filter(product -> {
                double productPrice = Double.parseDouble(product.getPrice());
                return productPrice >= minPrice && productPrice <= maxPrice;
            }).collect(Collectors.toList());
        }
        if (brand != null && !brand.isEmpty()) {
            products = products.stream().filter(product -> brand.equals(product.getBrand())).collect(Collectors.toList());
        }
        if (color != null && !color.isEmpty()) {
            products = products.stream().filter(product -> color.equals(product.getColor())).collect(Collectors.toList());
        }
        return products;
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.search(keyword);
    }

    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }
    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    public void update(Product product) {
        Optional<Product> existingProduct = productRepo.findById(product.getId());
        if (existingProduct.isPresent()) {
            Product prod = existingProduct.get();
            prod.setName(product.getName());
            prod.setCategory(product.getCategory());
            prod.setBrand(product.getBrand());
            prod.setColor(product.getColor());
            prod.setPrice(product.getPrice());
            prod.setDescription(product.getDescription());
            if (product.getImage() != null) {
                prod.setImage(product.getImage());
            }
            productRepo.save(prod);
        }
    }



}
