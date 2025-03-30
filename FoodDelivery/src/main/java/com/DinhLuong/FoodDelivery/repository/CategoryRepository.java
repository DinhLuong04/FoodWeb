package com.DinhLuong.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    
}
