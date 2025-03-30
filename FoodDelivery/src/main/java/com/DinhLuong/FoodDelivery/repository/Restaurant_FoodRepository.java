package com.DinhLuong.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Restaurant_Food;
import com.DinhLuong.FoodDelivery.entity.keys.KeyRestaurant_Food;
@Repository
public interface Restaurant_FoodRepository extends JpaRepository<Restaurant_Food,KeyRestaurant_Food> {
    
}
