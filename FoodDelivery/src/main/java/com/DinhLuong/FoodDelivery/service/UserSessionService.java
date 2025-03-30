package com.DinhLuong.FoodDelivery.service;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserSessionService {
    private final Set<Integer> onlineUsers = ConcurrentHashMap.newKeySet();

    public void userConnected(int userId) {
        onlineUsers.add(userId);
    }

    public void userDisconnected(int userId) {
        onlineUsers.remove(userId);
    }

    public boolean isUserOnline(int userId) {
        return onlineUsers.contains(userId);
    }
}
