package com.DinhLuong.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.ChatMessages;
import com.DinhLuong.FoodDelivery.entity.Users;
@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessages,Integer> {
    List<ChatMessages> findByUsersSenderAndUsersReceiver(Users usersSender, Users usersReceiver);

    
}
