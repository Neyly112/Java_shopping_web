package vn.tdtu.edu.springcomerce.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "`order`") // Đặt tên bảng trong dấu nháy ngược
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private String name;
    private String phone;
    private String address;
    private Date date;
    private String status;
    private Double total;

    public Order(long l, Object o, Object o1, String pending) {
        this.id = l;
        this.user = (User) o;
        this.orderItems = new ArrayList<OrderItem>();
    }

    public Order() {

    }
}
