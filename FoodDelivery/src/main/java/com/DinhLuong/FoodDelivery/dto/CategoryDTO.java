package com.DinhLuong.FoodDelivery.dto;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private  int id;
    private  String nameCate ;
    private Date creatDate;
    private List<FoodDTO> menu;
   
}
