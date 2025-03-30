package com.DinhLuong.FoodDelivery.entity;

import com.DinhLuong.FoodDelivery.entity.keys.KeyRestaurant_Food;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name="Restaurant_Food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant_Food {
    @EmbeddedId
    KeyRestaurant_Food keys;
    @ManyToOne
    @JoinColumn(name="res_id", insertable = false, updatable = false)
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name="food_id", insertable = false, updatable = false)
    private Food food;
    
}
