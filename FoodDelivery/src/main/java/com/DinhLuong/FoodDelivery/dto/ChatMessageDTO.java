package com.DinhLuong.FoodDelivery.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private int senderId;
    private int receiverId;
    private String message;
    private Date sentAt;

}
