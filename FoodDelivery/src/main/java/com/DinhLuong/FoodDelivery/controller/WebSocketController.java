package com.DinhLuong.FoodDelivery.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.DinhLuong.FoodDelivery.payload.request.MessageDTO;

import java.security.Principal;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/chat")// /app/message
    @SendTo("/Chatroom/public")
    public MessageDTO MessaGe (@Payload MessageDTO message) {
        System.out.println("message : "+message.getContent() +"to "+ message.getTo());
                return message;
    }
    @MessageMapping("/private-message")
    public MessageDTO privateMess(@Payload MessageDTO message){
        messagingTemplate.convertAndSendToUser(message.getContent(),"/private",message);//user/luong/private
        System.out.println("message private : "+message.getContent() +"to "+ message.getTo());
        return message;
    }

    

}
