package vn.tdtu.edu.springcomerce.models;


import lombok.Getter;

@Getter
public class AddToCartRequest {
    // Getters and Setters
    private Long productId;
    private int quantity;
    private double price;

    // Constructors
    public AddToCartRequest() {}

    public AddToCartRequest(Long productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

