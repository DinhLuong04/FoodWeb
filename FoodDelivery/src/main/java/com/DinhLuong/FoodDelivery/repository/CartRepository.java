package com.DinhLuong.FoodDelivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Cart;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
     Optional<Cart> findByUsersAndRestaurant(Users users, Restaurant restaurant);
     Optional<Cart> findByUsers(Users users);
}
