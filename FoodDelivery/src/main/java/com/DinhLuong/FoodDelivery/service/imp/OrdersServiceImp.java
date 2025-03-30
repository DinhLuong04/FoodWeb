package com.DinhLuong.FoodDelivery.service.imp;

import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.payload.request.CreateOrder;

public interface OrdersServiceImp {
    boolean CreatOrder(CreateOrder createOrder);
    
}
