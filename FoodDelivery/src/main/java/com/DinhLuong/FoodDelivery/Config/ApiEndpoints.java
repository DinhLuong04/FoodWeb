package com.DinhLuong.FoodDelivery.Config;

import java.util.Arrays;
import java.util.List;

public class ApiEndpoints {
    public static final String[] PUBLIC_ENDPOINTS = {
        "/login/**", "/swagger-ui/**", "/v3/api-docs/**",
        "/Restaurant/getRes", "/chat/**", 
        "/ws/**", "/chatroom/**", "/app/**", "/private-message/**"
    };

    public static final String[] ADMIN_ENDPOINTS = {
        "/admin/**", "/Restaurant/update", "/Restaurant/delete"
    };

    public static final String[] USER_OR_ADMIN_ENDPOINTS = {
        "/User/**",
        "/{ResId}/add-Rating/{UserId}"
        ,
         "/Restaurant/getAllRes"
        ,
        "/Order/**"
        ,
        "/Cart/**"
    };
    public static List<String> getPublicEndpoints() {
        return Arrays.asList(PUBLIC_ENDPOINTS);
    }
}
