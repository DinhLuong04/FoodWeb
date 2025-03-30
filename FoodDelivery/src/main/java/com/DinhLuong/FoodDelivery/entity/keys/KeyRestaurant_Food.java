package com.DinhLuong.FoodDelivery.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyRestaurant_Food implements Serializable {
    @Column(name = "res_id")
    private int res_id;

    @Column(name = "food_id")
    private int food_id;

}
