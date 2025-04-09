package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import com.DinhLuong.FoodDelivery.payload.request.SignUpRequest;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;

public interface ShipperServiceImp {
    String register(SignUpRequest signUpRequest);
    List<OrdersRepone> getOrderForShipper(String UserName);
}
