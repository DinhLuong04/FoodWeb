package com.DinhLuong.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.payload.request.CreateCart;
import com.DinhLuong.FoodDelivery.payload.respone.CartRespone;
import com.DinhLuong.FoodDelivery.service.CartService;

@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addCart(Authentication authentication, @RequestBody CreateCart createCart) {
        responeData responeData = new responeData();
        try {
            String username = authentication.getName();
            boolean ischecked = cartService.createCart(username, createCart);
            if (ischecked) {
                responeData.setStatus(200);
                responeData.setMessage("200");
                return ResponseEntity.ok(responeData);
            } else {
                responeData.setStatus(404);
                responeData.setMessage("Not found");
                return ResponseEntity.ok(responeData);
            }

        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(404).body(responeData);
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<responeData> deleteCartItems(@RequestBody List<Integer> items) {
        responeData response = new responeData();

        try {
            if (cartService.deleteCartItem(items)) {
                response.setStatus(200);
                response.setMessage("Deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setStatus(404);
                response.setMessage("not found cart ids");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage("Internal Server Error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/getCart")
    public ResponseEntity<responeData> getCart(Authentication authentication) {
        responeData response = new responeData();

        try {
            String usersName=authentication.getName();
            List<CartRespone> list=cartService.getCart(usersName);
            response.setStatus(200);
            response.setMessage("success");
            response.setData(list);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage("Internal Server Error: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
