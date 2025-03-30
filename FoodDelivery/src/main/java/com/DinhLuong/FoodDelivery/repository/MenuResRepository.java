package com.DinhLuong.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DinhLuong.FoodDelivery.entity.MenuRestaurant;
import com.DinhLuong.FoodDelivery.entity.keys.KeyMenuRestaurant;

public interface MenuResRepository extends JpaRepository<MenuRestaurant,KeyMenuRestaurant> {
    
}
