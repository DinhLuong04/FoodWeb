package com.DinhLuong.FoodDelivery.entity;

import com.DinhLuong.FoodDelivery.entity.keys.KeyOrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="OrderDetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @EmbeddedId
    KeyOrderDetail keys;
    @ManyToOne
    @JoinColumn(name="order_id",insertable=false,updatable=false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name="food_id",insertable = false,updatable = false)
    private Food food;
    @Column(name="create_date")
    private Date createDate;
    @Column(name = "quantity")
    private int quantity;

}
