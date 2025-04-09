package com.DinhLuong.FoodDelivery.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomAccessDeniedHandler  implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String jsonResponse = "{\"message\": \"Bạn không có quyền truy cập vào tài nguyên này!\"}";
                response.getWriter().write(jsonResponse);
    }
    
}
