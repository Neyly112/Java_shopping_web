package vn.tdtu.edu.springcomerce.models;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Getter
@Setter

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double price;

    public double getTotalPrice() {
        return price * quantity;
    }
}
