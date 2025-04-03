package com.DinhLuong.FoodDelivery.dto.Enum;
import lombok.*;
@Getter

@AllArgsConstructor

public enum OrderStatus {
    PENDING("pending"),
    PROCESSING("processing"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");
    private final String value;

}
