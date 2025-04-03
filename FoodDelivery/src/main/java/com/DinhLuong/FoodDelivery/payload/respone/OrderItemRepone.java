package com.DinhLuong.FoodDelivery.payload.respone;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRepone {
    private int id;
    private String foodName;
    private int quantity;
    private double price;
}
