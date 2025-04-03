package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.payload.request.CreateOrder;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;

public interface OrdersServiceImp {
    boolean CreatOrder(CreateOrder createOrder,String username);
    List<OrdersRepone> getListOrder(String username);
    OrdersRepone getOrder(int OrderId,String username);
}
