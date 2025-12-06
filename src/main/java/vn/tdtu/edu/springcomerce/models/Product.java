package vn.tdtu.edu.springcomerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private String price;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;

    public Product(String name, String category, String brand, String color ,double price, String description, String imagePath) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.color = color;
        this.price = String.valueOf(price);
        this.description = description;
        this.image = imagePath;
    }

    public Product(long l, String product1, String category1, String brand1, String color1, String number, String description1, String image) {
        this.id = l;
        this.name = product1;
        this.category = category1;
        this.brand = brand1;
        this.color = color1;
        this.price = number;
        this.description = description1;
        this.image = image;
    }
}
