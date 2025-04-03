package com.DinhLuong.FoodDelivery.payload.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder {
    private int resid;
    private int addressid;
    private List<OrdersItemRequest> listFood;
   
}
