package com.DinhLuong.FoodDelivery.service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.FoodDTO;
import com.DinhLuong.FoodDelivery.entity.Category;
import com.DinhLuong.FoodDelivery.entity.Food;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Restaurant_Food;
import com.DinhLuong.FoodDelivery.entity.keys.KeyRestaurant_Food;
import com.DinhLuong.FoodDelivery.repository.FoodRepository;
import com.DinhLuong.FoodDelivery.repository.RestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.Restaurant_FoodRepository;
import com.DinhLuong.FoodDelivery.service.imp.FoodServiceImp;
@Service
public class FoodService implements FoodServiceImp {

    @Autowired 
    ImgBBService imgBBService;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    Restaurant_FoodRepository restaurant_foodRepository;

     public String checkAndUploadImage(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Mã hóa ảnh mới sang Base64
                String newImageBase64 = Base64.getEncoder().encodeToString(file.getBytes());

                // Kiểm tra ảnh đã tồn tại trên ImgBB chưa
                String existingImageUrl = imgBBService.findImageOnImgBB(newImageBase64);
                
                if (existingImageUrl != null) {
                    return existingImageUrl; 
                } else {
                    return imgBBService.upLoadImage(file); 
                }
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi đọc ảnh: " + e.getMessage());
            }
        }
        return null; 
    }

    @Override
    public FoodDTO addFood(String title, MultipartFile file, String timeShip, Double price,Category category,int ResId) {
        Food newFood= new Food();
        newFood.setTitle(title);
        String imageUrl = checkAndUploadImage(file);
        if (imageUrl != null) {
            newFood.setImage(imageUrl);
        }
        newFood.setTimeShip(timeShip);
        newFood.setPrice(price);
        newFood.setCategory(category);
        foodRepository.save(newFood);
        FoodDTO foodDTO =new FoodDTO(newFood.getId(), newFood.getTitle(),newFood.getImage() ,newFood.getTimeShip(),newFood.getPrice());
        Restaurant restaurant=restaurantRepository.findById(ResId).orElseThrow(()-> new RuntimeException("Not found ResID: "+ResId));
        KeyRestaurant_Food key=new KeyRestaurant_Food(ResId,newFood.getId());
        Restaurant_Food restaurant_Food=new Restaurant_Food(key,restaurant, newFood) ;
        restaurant_foodRepository.save(restaurant_Food);
        return foodDTO;
    
}

    @Override
    public List<FoodDTO> getAll() {
        List<Food> listfood=foodRepository.findAll();
        List<FoodDTO> listFoodDTO=new ArrayList<>();
        for(Food x: listfood){
            FoodDTO foodDTO=new FoodDTO();
            foodDTO.setId(x.getId());
            foodDTO.setTitle(x.getTitle());
            foodDTO.setImage(x.getImage());
            foodDTO.setTimeShip(x.getTimeShip());
            foodDTO.setPrice(x.getPrice());
            listFoodDTO.add(foodDTO);
        }
        return listFoodDTO;
    }

    @Override
    public FoodDTO getFood(int id) {
        Food food=foodRepository.findById(id).orElseThrow(()->new RuntimeException("Not found foodId :"+id));
        FoodDTO foodDTO=new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setTitle(food.getTitle());
        foodDTO.setImage(food.getImage());
        foodDTO.setTimeShip(food.getTimeShip());
        foodDTO.setPrice(food.getPrice());
        return foodDTO;
    }

    @Override
    public FoodDTO update(int foodId, String title, MultipartFile file, String timeShip, Double price) {
        Food food=foodRepository.findById(foodId).orElseThrow(()->new RuntimeException("Not found foodId :"+foodId));
        food.setTitle(title);
        String imageUrl = checkAndUploadImage(file);
        if (imageUrl != null) {
            food.setImage(imageUrl);
        }
        food.setTimeShip(timeShip);
        food.setPrice(price);
        foodRepository.save(food);
        FoodDTO foodDTO=new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setTitle(food.getTitle());
        foodDTO.setImage(food.getImage());
        foodDTO.setTimeShip(food.getTimeShip());
        foodDTO.setPrice(food.getPrice());
        return foodDTO;
    }

    @Override
    public boolean delete(int foodId) {
        Food food=foodRepository.findById(foodId).orElseThrow(()->new RuntimeException("Not found foodId :"+foodId));
        foodRepository.delete(food);
        return true;
    }
}
