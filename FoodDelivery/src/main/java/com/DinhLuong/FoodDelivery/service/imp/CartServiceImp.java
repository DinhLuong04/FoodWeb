package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import com.DinhLuong.FoodDelivery.payload.request.CreateCart;
import com.DinhLuong.FoodDelivery.payload.respone.CartRespone;

public interface CartServiceImp {
    List<CartRespone> getCart(String username);
    boolean createCart(String username,CreateCart createCart);
    boolean deleteCartItem(List<Integer> items);
}
