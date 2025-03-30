package com.DinhLuong.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Cart_Items;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Items,Integer> {
    
} 
