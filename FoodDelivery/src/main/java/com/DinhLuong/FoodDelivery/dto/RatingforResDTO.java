package com.DinhLuong.FoodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingforResDTO {
    private int id;
    private int userId;
    private String userName;
    private String content;
    private int ratePoint;
   
}
