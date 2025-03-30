package com.DinhLuong.FoodDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.payload.request.CreateCart;
import com.DinhLuong.FoodDelivery.service.CartService;



@RestController
@RequestMapping("/Cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@RequestBody CreateCart createCart)
    {
        responeData responeData= new responeData();
        try {
            boolean ischecked=cartService.createCart(createCart);
            if(ischecked){
                responeData.setStatus(200);
                responeData.setMessage("200");
                return ResponseEntity.ok(responeData);
            }
            else{
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
}
