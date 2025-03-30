package com.DinhLuong.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.AddressDTO;
import com.DinhLuong.FoodDelivery.dto.UserDTO;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.service.AddressService;
import com.DinhLuong.FoodDelivery.service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @GetMapping("/getMyinfor")
    public ResponseEntity<?> getMyinfor(Authentication authentication) {
        responeData responeData = new responeData();
        try {
           
            String userName = authentication.getName();
            System.out.println(userName);
            UserDTO userDTO = userService.getMyInfor(userName);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            responeData.setData(userDTO);
            
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(responeData); 
        }
    }

    //Update infor
    @PutMapping("/UpdateInfor")
    public ResponseEntity<?> UpdateInfor(Authentication authentication
    ,@RequestParam("fullname") String fullname
    ,@RequestParam("password") String password
    ,@RequestParam(value = "file", required = false) MultipartFile file
    ){
        responeData responeData = new responeData();
        try {
            String userName = authentication.getName();
            UserDTO userDTO=userService.UpdateInfor(userName, fullname, password, file);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            responeData.setData(userDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(responeData); 
        }

    }

    @GetMapping("/getAll/Address")
    public ResponseEntity<?> addAddress(Authentication authentication)
    {
        responeData responeData=new responeData();
        try {
            String username=authentication.getName();
            List<AddressDTO> list=addressService.getAll(username);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(list);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(responeData); 
        }
    }

    @PostMapping("/add/Address")
    public ResponseEntity<?> add(Authentication authentication,
    @RequestParam("fullAddress") String fullAddress,
    @RequestParam("city") String city,
    @RequestParam("district") String district,
    @RequestParam("ward") String ward,
    @RequestParam("phone") String phone
    ) 
    {
        responeData responeData =new responeData();
        try {
            String username=authentication.getName();
            AddressDTO addressDTO=addressService.addAddress(username, fullAddress, city, district, ward, phone);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(addressDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(responeData);
        }
    }

    @PutMapping("/update/Address")
    public ResponseEntity<?> update(@RequestParam("AddressID") int AddressID,
    @RequestParam("fullAddress") String fullAddress,
    @RequestParam("city") String city,
    @RequestParam("district") String district,
    @RequestParam("ward") String ward, 
    @RequestParam("phone") String phone)
    {
        responeData responeData=new responeData();
        try {

            AddressDTO addressDTO=addressService.UpdateAddress(AddressID, fullAddress, city, district, ward, phone);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(addressDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(responeData);
        } 
    }

    @DeleteMapping("/delete/Address")
    public ResponseEntity<?> delete(@RequestParam("AddressID") int AddressID){
        responeData responeData=new responeData();
        try {
            boolean isDelete=addressService.DeleteAddress(AddressID);
            if(isDelete){
                responeData.setStatus(200);
                responeData.setMessage("success");
                return ResponseEntity.ok(responeData);
            }
            else {
                responeData.setStatus(404);
                responeData.setMessage("Not found");
                return ResponseEntity.status(404).body(responeData);
            }
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(responeData);
        }
               
    }
}
