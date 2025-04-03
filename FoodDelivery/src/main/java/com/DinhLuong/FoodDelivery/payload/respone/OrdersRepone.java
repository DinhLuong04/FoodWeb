package com.DinhLuong.FoodDelivery.payload.respone;

import java.util.Date;
import java.util.List;

import com.DinhLuong.FoodDelivery.dto.AddressDTO;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRepone {
    private int orderId;
    private Date createDate;
    private double totalPrice;
    private String status;
    private String ResName;
    private AddressDTO address;
    private List<OrderItemRepone> items;
}
