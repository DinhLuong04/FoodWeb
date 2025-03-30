package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.RestaurantDTO;
import com.DinhLuong.FoodDelivery.entity.Restaurant;

public interface RestaurantImp {
    List<RestaurantDTO> getAllRes();
    RestaurantDTO getByID(int id);
    Restaurant addRestaurant(String title, String subtitle, String description, MultipartFile file, Boolean isFreeship, String address);
    String addCategory(int resID,int cateID);
    Restaurant Update(int Id,String title, String subtitle, String description, MultipartFile file, Boolean isFreeship, String address);
    boolean deleteRes(int id);
}
