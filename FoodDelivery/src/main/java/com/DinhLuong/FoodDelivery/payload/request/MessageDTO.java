package com.DinhLuong.FoodDelivery.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String to;
    private String content;
}
