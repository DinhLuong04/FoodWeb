package com.DinhLuong.FoodDelivery.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private int id;
    private String title;
    private String image;
    private String timeShip;
    private double price;

   
}
