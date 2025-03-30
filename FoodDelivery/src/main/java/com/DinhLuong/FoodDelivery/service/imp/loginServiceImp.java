package com.DinhLuong.FoodDelivery.service.imp;

import com.DinhLuong.FoodDelivery.dto.UserDTO;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.payload.request.SignUpRequest;

import java.util.List;

public interface loginServiceImp {
   
    boolean CheckLogin(String username, String password);
    Users findbyEmail(String email);
    boolean RegisterUser(SignUpRequest signUpRequest);

}
