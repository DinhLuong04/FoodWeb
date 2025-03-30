package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import com.DinhLuong.FoodDelivery.dto.AddressDTO;

public interface AddressServiceImp {
    List<AddressDTO> getAll(String username); 
    AddressDTO addAddress(String username,String fullAddress,String city,String district,String ward,String phone);
    AddressDTO UpdateAddress(int AddressID,String fullAddress,String city,String district,String ward,String phone);
    Boolean DeleteAddress(int AddressID );
}
