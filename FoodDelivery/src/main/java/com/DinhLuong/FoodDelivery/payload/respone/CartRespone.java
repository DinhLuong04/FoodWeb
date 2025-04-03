package com.DinhLuong.FoodDelivery.payload.respone;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRespone {
    private Integer cartItemId;  
    private int foodId;  
    private String foodName; 
    private String image;     
    private double price;    
    private Integer quantity;     
    private Integer resid;        
    private String resName;      
}
