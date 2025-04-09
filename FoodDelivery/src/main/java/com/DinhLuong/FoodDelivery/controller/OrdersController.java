package com.DinhLuong.FoodDelivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.DinhLuong.FoodDelivery.dto.OrderDetailDTO;
import com.DinhLuong.FoodDelivery.dto.Enum.OrderStatus;
import com.DinhLuong.FoodDelivery.entity.OrderDetail;
import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.payload.request.CreateOrder;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;
import com.DinhLuong.FoodDelivery.service.OrdersService;
import com.DinhLuong.FoodDelivery.service.ShipperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    ShipperService shipperService;

    @PostMapping("/Create")
    public ResponseEntity<?> createOrder(Authentication authentication,@RequestBody CreateOrder request) {
        responeData responeData = new responeData();
        try {
            String usersName=authentication.getName();
            boolean checkCreatOrder = ordersService.CreatOrder(request,usersName);
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

    @GetMapping("/getOrders")
    public ResponseEntity<?> getAll(Authentication authentication){
        
        responeData responeData=new responeData();
        try {
            String usersName=authentication.getName();
            System.out.println(usersName);
            List<OrdersRepone> data=ordersService.getListOrder(usersName);
            responeData.setData(data);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            e.printStackTrace();
            responeData.setStatus(500);
            responeData.setMessage("Lỗi hệ thống: " + e.getMessage());
            responeData.setData(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responeData);
        }
    }

    @GetMapping("/getOrderDetail/{Orderid}")
    public ResponseEntity<?> getOrdersByid(Authentication authentication,@PathVariable("Orderid") int Orderid){
        
        responeData responeData=new responeData();
        try {
            String usersName=authentication.getName();
            OrdersRepone data=ordersService.getOrder(Orderid,usersName);
            responeData.setData(data);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            e.printStackTrace();
            responeData.setStatus(500);
            responeData.setMessage("Lỗi hệ thống: " + e.getMessage());
            responeData.setData(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responeData);
        }
    }

    @PutMapping("/UpdateStatus/{orderid}/{status}")
    public ResponseEntity<?> updatestatus(@PathVariable("orderid") int orderid,
    @PathVariable("status") String status
    )
    {
        responeData responeData=new responeData();
        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            String data=ordersService.updateOrderStatus(orderid, newStatus);
            responeData.setData(data);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            e.printStackTrace();
            responeData.setStatus(500);
            responeData.setMessage("Lỗi hệ thống: " + e.getMessage());
            responeData.setData(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responeData);
        }
    }

    @GetMapping("/getOrdersForShip")
    public ResponseEntity<?> getOrderForShip(Authentication authentication){
        responeData responeData=new responeData();
        try {
            List<OrdersRepone> data=shipperService.getOrderForShipper(authentication.getName());
            responeData.setData(data);
            responeData.setStatus(200);
            responeData.setMessage("Success");
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
