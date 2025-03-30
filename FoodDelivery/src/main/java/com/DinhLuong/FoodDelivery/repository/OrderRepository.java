package com.DinhLuong.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    
}
