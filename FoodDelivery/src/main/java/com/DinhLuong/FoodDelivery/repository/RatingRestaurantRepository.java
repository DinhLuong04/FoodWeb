package com.DinhLuong.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.RatingRestaurant;
@Repository
public interface RatingRestaurantRepository extends JpaRepository<RatingRestaurant,Integer> {
    @Query(value = "select * from RatingRestaurant where res_id=?", nativeQuery = true)
    List<RatingRestaurant> findRatingbyResID(int id);
}
