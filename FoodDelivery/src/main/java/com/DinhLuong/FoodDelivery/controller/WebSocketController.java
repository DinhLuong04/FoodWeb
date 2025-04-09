package com.DinhLuong.FoodDelivery.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.DinhLuong.FoodDelivery.payload.request.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat") 
    @SendTo("/chatroom/public") 
    public MessageDTO MessaGe(@Payload MessageDTO message) {
        logger.info("Public message: {} to {}", message.getContent(), message.getTo());
        return message;
    }

    @MessageMapping("/private-message")
    public MessageDTO privateMess(@Payload MessageDTO message) {
        messagingTemplate.convertAndSendToUser(
            message.getTo(),
            "/private",
            message
        );
        logger.info("Private message: {} to {}", message.getContent(), message.getTo());
        return message;
    }
}
