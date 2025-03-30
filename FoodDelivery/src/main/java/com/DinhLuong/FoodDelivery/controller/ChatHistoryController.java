package com.DinhLuong.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.DinhLuong.FoodDelivery.entity.ChatMessages;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.repository.ChatMessageRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;

@RestController
@RequestMapping("/api/chat")
public class ChatHistoryController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserRepository usersRepository;

    @GetMapping("/history/{senderId}/{receiverId}")
    public List<ChatMessages> getChatHistory(
        @PathVariable int senderId,
        @PathVariable int receiverId) {
        
        Users sender = usersRepository.findById(senderId).orElse(null);
        Users receiver = usersRepository.findById(receiverId).orElse(null);

        if (sender == null || receiver == null) {
            return null;
        }
        return chatMessageRepository.findByUsersSenderAndUsersReceiver(sender, receiver);
    }
}
