package vn.tdtu.edu.springcomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import vn.tdtu.edu.springcomerce.Services.*;
import vn.tdtu.edu.springcomerce.models.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;


    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();
        List<String> colors = productService.getAllColors();
        List<String> prices = productService.getAllPrices();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("prices", prices);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/products/filter")
    public String filterProducts(@RequestParam(required = false) String category, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String brand, @RequestParam(required = false) String color, Model model) {
        List<Product> filteredProducts = productService.filterProducts(category, minPrice, maxPrice, brand, color);
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();
        List<String> colors = productService.getAllColors();
        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedColor", color);
        return "index";
    }

    @GetMapping("/productsUser/filter")
    public String filterProductsUser(@RequestParam(required = false) String category, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String brand, @RequestParam(required = false) String color, Model model) {
        List<Product> filteredProducts = productService.filterProducts(category, minPrice, maxPrice, brand, color);
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();
        List<String> colors = productService.getAllColors();
        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedColor", color);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "indexUser";
    }

    @GetMapping("/productsAd/filter")
    public String filterProductsAd(@RequestParam(required = false) String category, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String brand, @RequestParam(required = false) String color, Model model) {
        List<Product> filteredProducts = productService.filterProducts(category, minPrice, maxPrice, brand, color);
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();
        List<String> colors = productService.getAllColors();
        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedColor", color);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "indexAdmin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

    @GetMapping("/indexUser")
    public String indexUser(@RequestParam(required = false) String category, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String brand, @RequestParam(required = false) String color, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        List<Product> products = productService.getAllProducts();
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();
        List<String> colors = productService.getAllColors();
        List<String> prices = productService.getAllPrices();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("prices", prices);
        model.addAttribute("products", products);
        return "indexUser";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "indexSearch";
    }

    @GetMapping("/searchUser")
    public String searchProductsUser(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "searchUser";
    }

    @GetMapping("/searchAd")
    public String searchProductsAd(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "searchAdmin";
    }

    @GetMapping("/indexAdmin")
    public String indexAdmin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        List<Product> products = productService.getAllProducts();
        List<String> categories = productService.getAllCategories();
        List<String> brands = productService.getAllBrands();
        List<String> colors = productService.getAllColors();
        List<String> prices = productService.getAllPrices();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);
        model.addAttribute("prices", prices);
        model.addAttribute("products", products);
        return "indexAdmin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserCheck userCheck = new UserCheck();
        model.addAttribute("userCheck", userCheck);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userCheck") UserCheck userCheck, Model model, BindingResult
            bindingResult) {
        User usernameExist = userService.findByUsername(userCheck.getUsername());
        if ((usernameExist != null) && (usernameExist.getUsername() != null) && !(usernameExist.getUsername().isEmpty())) {
            bindingResult.rejectValue("username", null, "Username have been used");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCheck", userCheck);
            return "register";
        }
        userService.saveUser(userCheck);
        return "redirect:/login";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "productDetail";
    }

    @GetMapping("/productsUser/{id}")
    public String getProductUserDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "productDetailUser";
    }

    @GetMapping("/productsAd/{id}")
    public String getProductUserDetailAd(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "productDetailAd";
    }



    @GetMapping("/cart")
    public String Cart(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Cart cart = cartService.getCartByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
        }
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        double totalPrice = cart.getItems().stream().mapToDouble(item -> Double.parseDouble(item.getProduct().getPrice()) * item.getQuantity()).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @GetMapping("/cartAd")
    public String CartAd(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Cart cart = cartService.getCartByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
        }
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        double totalPrice = cart.getItems().stream().mapToDouble(item -> Double.parseDouble(item.getProduct().getPrice()) * item.getQuantity()).sum();
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        return "cartAd";
    }

    @GetMapping("/cart-item-count")
    public int getCartItemCount(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Cart cart = cartService.getCartByUser(user);
        return cart != null ? cart.getItems().size() : 0;
    }

    @PostMapping("/add-to-cart")
    @ResponseBody
    public ResponseEntity<?> addToCart(@RequestParam Long productId,
                                       @RequestParam int quantity,
                                       @RequestParam double price,
                                       Authentication authentication) {
        try {
            User user = userService.findByUsername(authentication.getName());
            cartService.addToCart(user, productId, quantity, price);
            return ResponseEntity.ok(Map.of("message", "Product added to cart successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/add-to-cartt")
    public String addToCartt(@RequestParam Long productId,
                             @RequestParam int quantity,
                             @RequestParam double price,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByUsername(authentication.getName());

            cartService.addToCart(user, productId, quantity, price);

            redirectAttributes.addFlashAttribute("successMessage", "Product added to cart successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add product to cart: " + e.getMessage());
        }

        return "redirect:/indexUser";
    }

    @PostMapping("/add-to-cartAd")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity,
                            @RequestParam double price,
                            Authentication authentication,
                            RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByUsername(authentication.getName());

            cartService.addToCart(user, productId, quantity, price);

            redirectAttributes.addFlashAttribute("successMessage", "Product added to cart successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add product to cart: " + e.getMessage());
        }

        return "redirect:/indexAdmin";
    }

    @PostMapping("/add-to-cartAdd")
    @ResponseBody
    public ResponseEntity<Map<String, String>> addToCart(@RequestParam Long productId,
                                                         @RequestParam int quantity,
                                                         @RequestParam double price,
                                                         Principal principal) {
        Map<String, String> response = new HashMap<>();
        try {
            User user = userService.findByUsername(principal.getName());
            cartService.addToCart(user, productId, quantity, price);

            response.put("message", "Product added to cart successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Failed to add product to cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @PostMapping("/delete/{id}")
    public String removeFromCart(@PathVariable(value = "id") Long productId, Authentication authentication,
                                 RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByUsername(authentication.getName());
            cartService.removeFromCart(user, productId);
            if (cartService.isCartEmpty(user)) {
                redirectAttributes.addFlashAttribute("warningMessage", "Giỏ hàng của bạn đang trống.");
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Item removed successfully!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cart";
    }


    @PostMapping("/deleteAd/{id}")
    public String removeFromCartAd(@PathVariable(value = "id") Long productId, Authentication authentication,
                                   RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByUsername(authentication.getName());
            cartService.removeFromCart(user, productId);

            if (cartService.isCartEmpty(user)) {
                redirectAttributes.addFlashAttribute("warningMessage", "Giỏ hàng của bạn đang trống.");
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Item removed successfully!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cartAd";
    }


    @GetMapping("/orders")
    public String getUserOrders(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Order> orders = orderService.findOrdersByUser(user);
        if (orders.isEmpty()) {
            model.addAttribute("You have not placed any orders yet.", orders);
        }
        model.addAttribute("orders", orders);

        return "orderList";
    }

    @GetMapping("/ordersAd")
    public String getUserOrdersAd(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Order> orders = orderService.findOrdersByUser(user);
        if (orders.isEmpty()) {
            model.addAttribute("You have not placed any orders yet.", orders);
        }
        model.addAttribute("orders", orders);

        return "orderListAd";
    }

    @GetMapping("/orders/{id}")
    public String getOrderDetails(@PathVariable Long id, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Order order = orderService.findOrderByIdAndUser(id, user);
        model.addAttribute("order", order);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "orderDetail";
    }

    @GetMapping("/ordersAd/{id}")
    public String getOrderDetailsAd(@PathVariable Long id, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Order order = orderService.findOrderByIdAndUser(id, user);
        model.addAttribute("order", order);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "orderDetailAd";
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam("name") String name, @RequestParam("phone") String
            phone, @RequestParam("address") String address, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Order order = new Order();
        order.setUser(user);
        order.setName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setDate(new Date());
        order.setStatus("Pending");
        List<OrderItem> orderItems = orderService.getOrderItemsFromCart(user);
        order.setOrderItems(orderItems);
        double total = orderItems.stream().mapToDouble(item -> {
            double price = Double.parseDouble(item.getProduct().getPrice());
            return price * item.getQuantity();
        }).sum();
        order.setTotal(total);
        orderService.saveOrder(order);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        model.addAttribute("message", "Order placed successfully!");
        model.addAttribute("order", order);
        return "orderSuccess";
    }

    @PostMapping("/place-orderAd")
    public String placeOrderAd(@RequestParam("name") String name, @RequestParam("phone") String
            phone, @RequestParam("address") String address, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Order order = new Order();
        order.setUser(user);
        order.setName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setDate(new Date());
        order.setStatus("Pending");
        List<OrderItem> orderItems = orderService.getOrderItemsFromCart(user);
        order.setOrderItems(orderItems);
        double total = orderItems.stream().mapToDouble(item -> {
            double price = Double.parseDouble(item.getProduct().getPrice());
            return price * item.getQuantity();
        }).sum();
        order.setTotal(total);
        orderService.saveOrder(order);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        model.addAttribute("message", "Order placed successfully!");
        model.addAttribute("order", order);
        return "orderSuccessAd";
    }

    @PostMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody CartItemUpdateRequest request) {
        try {
            System.out.println("Request received: itemId=" + request.getItemId() + ", quantity=" + request.getQuantity());

            // Kiểm tra dữ liệu đầu vào
            if (request.getQuantity() < 1) {
                System.err.println("Invalid quantity: " + request.getQuantity());
                return ResponseEntity.badRequest().body("Quantity must be at least 1");
            }

            cartItemService.updateCartItemQuantity(request.getItemId(), request.getQuantity());
            System.out.println("Quantity updated successfully");
            return ResponseEntity.ok("Quantity updated successfully");
        } catch (RuntimeException e) {
            System.err.println("Failed to update quantity: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update quantity: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @PostMapping("/updateQuantity1")
    public ResponseEntity<String> updateQuantity1(@RequestBody CartItemUpdateRequest request) {
        try {
            System.out.println("Request received: itemId=" + request.getItemId() + ", quantity=" + request.getQuantity());

            if (request.getQuantity() < 1) {
                System.err.println("Invalid quantity: " + request.getQuantity());
                return ResponseEntity.badRequest().body("Quantity must be at least 1");
            }

            cartItemService.updateCartItemQuantity(request.getItemId(), request.getQuantity());
            System.out.println("Quantity updated successfully");
            return ResponseEntity.ok("Quantity updated successfully");
        } catch (RuntimeException e) {
            System.err.println("Failed to update quantity: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update quantity: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("/products")
    public String showProducts(Model model, Authentication authentication) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "productList";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("imageFile") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        try {
            String fileName = "default.png";
            if (!file.isEmpty()) {
                fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/";
                FileUploadUtil.saveFile(uploadDir, fileName, file);
                product.setImage(fileName);
            } else {
                product.setImage("default.png");
            }
            productService.save(product);
            redirectAttributes.addFlashAttribute("msg", "Product saved successfully!");
            return "redirect:/products";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error uploading file: " + e.getMessage());
            return "redirect:/products";
        }
    }

    @PostMapping("/products/update")
    public RedirectView updateProduct(Product product,
                                      @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                      RedirectAttributes redirectAttributes) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = imageFile.getOriginalFilename();
                FileUploadUtil.saveFile("src/main/resources/static/images", imageName, imageFile);
                product.setImage(imageName);
            }
            productService.update(product);

            redirectAttributes.addFlashAttribute("success", "Product updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating product: " + e.getMessage());
        }
        return new RedirectView("/products", true);
    }

    @GetMapping("/users")
    public String listUsers(Model model, Authentication authentication) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        return "userList";
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @GetMapping("/ordersAc")
    public String listOrders(Model model, Authentication authentication) {
        List<Order> orders = orderService.getAllOrders();
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        model.addAttribute("orders", orders);
        return "orderAccept";
    }

    @DeleteMapping("/deleteOrder/{id}")
    @ResponseBody
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "Order deleted successfully";
    }

    @GetMapping("/ordersView/{id}")
    public String viewOrder(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Order order = orderService.getOrderById(id);
        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("currentUserName", authentication.getName());
        }
        model.addAttribute("order", order);
        return "orderView";
    }

    @PutMapping("/acceptOrder/{id}")
    @ResponseBody
    public ResponseEntity<String> acceptOrder(@PathVariable("id") Long id) {
        try {
            orderService.acceptOrder(id);
            return ResponseEntity.ok("Order accepted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error accepting order: " + e.getMessage());
        }
    }
    @GetMapping("/about")
    public String about(Model model, Authentication authentication) {
        return "about";
    }
}