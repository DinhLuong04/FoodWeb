package com.DinhLuong.FoodDelivery.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyOrderDetail implements Serializable {
    @Column(name="order_id")
    private int orderId;
    @Column(name="food_id")
    private int foodId;
   
}
