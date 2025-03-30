package com.DinhLuong.FoodDelivery.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private String image;
    private boolean isFreeship;
    private String address;
    private Date openDate;
    private double Rating;
    private List<CategoryDTO> Category;
    private List<RatingforResDTO> ListRating;

}
