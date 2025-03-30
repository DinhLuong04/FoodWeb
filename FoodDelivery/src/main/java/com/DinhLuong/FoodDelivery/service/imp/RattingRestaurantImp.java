package com.DinhLuong.FoodDelivery.service.imp;

import com.DinhLuong.FoodDelivery.entity.RatingRestaurant;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Users;

public interface RattingRestaurantImp {
    RatingRestaurant addRate(int userId , int restaurantId,String content, int ratePoint);
}
