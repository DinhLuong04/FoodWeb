package com.DinhLuong.FoodDelivery.Config;

public class ApiEndpoints {
    public static final String[] PUBLIC_ENDPOINTS = {
        "/Cart/**", "/login/**", "/swagger-ui/**", "/v3/api-docs/**",
        "/Restaurant/getRes", "/chat/**", "/user/**", "/Order/**",
        "/ws/**", "/chatroom/**", "/app/**", "/private-message/**"
    };

    public static final String[] ADMIN_ENDPOINTS = {
        "/admin/**", "/Restaurant/update", "/Restaurant/delete"
    };

    public static final String[] USER_OR_ADMIN_ENDPOINTS = {
        "/{ResId}/add-Rating/{UserId}", "/Restaurant/getAllRes"
    };
}
