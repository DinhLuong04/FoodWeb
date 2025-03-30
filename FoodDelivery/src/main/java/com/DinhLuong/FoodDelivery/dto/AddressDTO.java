package com.DinhLuong.FoodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private int id;
    private String fullAddress;
    private String city;
    private String district;
    private String ward;
    private String phone;
}
