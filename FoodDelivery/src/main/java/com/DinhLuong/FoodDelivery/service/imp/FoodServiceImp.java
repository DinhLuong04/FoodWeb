package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.FoodDTO;
import com.DinhLuong.FoodDelivery.entity.Category;


public interface FoodServiceImp {
    List<FoodDTO> getAll();
    FoodDTO getFood(int id);
    FoodDTO addFood(String title,MultipartFile file,String timeShip,Double price,Category category,int ResId);
    FoodDTO update(int foodId,String title,MultipartFile file,String timeShip,Double price);
    boolean delete(int foodId);
}
