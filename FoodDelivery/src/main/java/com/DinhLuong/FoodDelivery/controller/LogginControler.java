package com.DinhLuong.FoodDelivery.controller;

import com.DinhLuong.FoodDelivery.payload.request.SignInRequest;
import com.DinhLuong.FoodDelivery.payload.request.SignUpRequest;
import com.DinhLuong.FoodDelivery.Ultil.JwtUltil;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.service.loginService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LogginControler {



    @Autowired
    private loginService loginService;

    @Autowired
    private JwtUltil jwtUltil;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signinRequest) {
        responeData responseData = new responeData();
        boolean isValid = loginService.CheckLogin(signinRequest.getEmail(), signinRequest.getPassword());
       
       
        if (isValid) {
            Users user = loginService.findbyEmail(signinRequest.getEmail());
           
            String role = user.getRoles().getRoleName();  
            String token = jwtUltil.generateToken(signinRequest.getEmail(), role);
   
            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("role", role);  
    
            return ResponseEntity.ok(response);
        } else {
            responseData.setStatus(401);
            responseData.setMessage("Invalid username or password");
            return ResponseEntity.status(responseData.getStatus()).body(responseData);
        }
    }
    

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpRequest SignUpRequest ){
        responeData respone = new responeData();
        try{
            respone.setData(loginService.RegisterUser(SignUpRequest));
            respone.setMessage("User registered successfully");
        }
        catch(Exception e){
            respone.setStatus(400);
            respone.setMessage(e.getMessage());
            return new ResponseEntity<>( respone, HttpStatus.OK);
        }


        return new ResponseEntity<>( respone, HttpStatus.OK);
    }

    @PostMapping("/registerShipper")
    public ResponseEntity<?> registerShiper(@RequestBody SignUpRequest SignUpRequest ){
        responeData respone = new responeData();
        try{
            respone.setData(loginService.RegisterUser(SignUpRequest));
            respone.setMessage("User registered successfully");
        }
        catch(Exception e){
            respone.setStatus(400);
            respone.setMessage(e.getMessage());
            return new ResponseEntity<>( respone, HttpStatus.OK);
        }


        return new ResponseEntity<>( respone, HttpStatus.OK);
    }


}
