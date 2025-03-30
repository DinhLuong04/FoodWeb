package com.DinhLuong.FoodDelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.entity.RatingRestaurant;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.repository.RatingRestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.RestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.RattingRestaurantImp;
@Service
public class RattingRestaurantService implements RattingRestaurantImp {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RatingRestaurantRepository ratingRestaurantRepository;

    @Override
    public RatingRestaurant addRate(int UserId,int ResId, String content, int ratePoint)  {
        Users user= userRepository.findById(UserId).orElseThrow(()->new RuntimeException("Not found User Id"+UserId));
        Restaurant res=restaurantRepository.findById(ResId).orElseThrow(()-> new RuntimeException("Not found ResID: "+ResId));
        RatingRestaurant ratingRestaurant =new RatingRestaurant();
        ratingRestaurant.setUsers(user);
        ratingRestaurant.setRestaurant(res);
        ratingRestaurant.setContent(content);
        ratingRestaurant.setRatePoint(ratePoint);
        return ratingRestaurantRepository.save(ratingRestaurant);
    }
    
}
