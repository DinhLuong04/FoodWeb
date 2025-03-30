package com.DinhLuong.FoodDelivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.DinhLuong.FoodDelivery.dto.OrderDetailDTO;
import com.DinhLuong.FoodDelivery.entity.OrderDetail;
import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.payload.request.CreateOrder;
import com.DinhLuong.FoodDelivery.service.OrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping("/Create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder request) {
        responeData responeData = new responeData();
        try {
            boolean checkCreatOrder = ordersService.CreatOrder(request);
            if(checkCreatOrder){
                responeData.setData(request);
                responeData.setStatus(200);
                responeData.setMessage("Success");
               
            }
            return ResponseEntity.ok(responeData);
           
        } catch (Exception e) {
            e.printStackTrace();
            responeData.setStatus(500);
            responeData.setMessage("Lỗi hệ thống: " + e.getMessage());
            responeData.setData(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responeData);
        }
    }
    
}
