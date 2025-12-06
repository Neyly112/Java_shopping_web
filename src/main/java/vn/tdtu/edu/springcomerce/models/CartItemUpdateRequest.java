package vn.tdtu.edu.springcomerce.models;

import lombok.Getter;
import lombok.Setter;


public class CartItemUpdateRequest {
    private Long itemId;
    private int quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

