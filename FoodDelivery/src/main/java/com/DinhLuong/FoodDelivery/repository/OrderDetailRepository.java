package com.DinhLuong.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.OrderDetail;
import com.DinhLuong.FoodDelivery.entity.keys.KeyOrderDetail;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,KeyOrderDetail> {
    
}
