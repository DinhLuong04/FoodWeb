package com.DinhLuong.FoodDelivery.service.imp;

import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.UserDTO;

public interface UsersServiceImp {
    UserDTO getMyInfor(String username); 
    UserDTO UpdateInfor(String username,String fullname,String password,MultipartFile file);
}
    
  
