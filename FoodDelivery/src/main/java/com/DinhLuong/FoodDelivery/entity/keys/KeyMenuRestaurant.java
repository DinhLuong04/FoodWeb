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
public class KeyMenuRestaurant implements Serializable {
    @Column(name="cate_id")
    private int cateId;
    @Column(name="res_id")
    private int resId;
    
}
