package com.DinhLuong.FoodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRestaurantDTO {
    private int id;
    private int userId;
    private String userName;
    private int restaurantId;
    private String restaurantName;
    private String content;
    private int ratePoint;

}


