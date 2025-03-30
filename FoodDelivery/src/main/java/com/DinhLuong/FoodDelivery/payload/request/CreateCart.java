package com.DinhLuong.FoodDelivery.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCart {
    private int user_id;
    private int res_id;
    private int food_id;
    private int quality;
}
