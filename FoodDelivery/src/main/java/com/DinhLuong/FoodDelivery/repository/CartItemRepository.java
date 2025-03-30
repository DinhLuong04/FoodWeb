package com.DinhLuong.FoodDelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Cart;
import com.DinhLuong.FoodDelivery.entity.Cart_Items;
import com.DinhLuong.FoodDelivery.entity.Food;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Items,Integer> {
     Optional<Cart_Items> findByCartAndFood(Cart cart, Food food);
} 
