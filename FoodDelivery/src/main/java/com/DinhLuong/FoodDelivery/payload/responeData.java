package com.DinhLuong.FoodDelivery.payload;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class responeData {
    private int status=200;
    private String message;
    private Object data;


}
