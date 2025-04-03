package com.DinhLuong.FoodDelivery.payload.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersItemRequest {
    private int foodid;
    private int quantity;
}
