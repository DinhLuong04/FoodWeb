package com.DinhLuong.FoodDelivery.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Food;
@Repository
public interface FoodRepository extends JpaRepository<Food,Integer> {
    Optional<Food> findByImage(String image);
}
