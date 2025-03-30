package com.DinhLuong.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.FoodDTO;
import com.DinhLuong.FoodDelivery.entity.Category;

import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.repository.CategoryRepository;
import com.DinhLuong.FoodDelivery.service.FoodService;

@RestController
@RequestMapping("/Food")
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired 
    CategoryRepository categoryRepository; 

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        responeData responeData=new responeData();
        try {
            List<FoodDTO> list=foodService.getAll();
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(list);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }


    @GetMapping("/getFood")
    public ResponseEntity<?> getFood(@RequestParam("id") int id){
        responeData responeData=new responeData();
        try {
            FoodDTO foodDTO=foodService.getFood(id);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(foodDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    }



    @PostMapping("/addFood")
    public ResponseEntity<?> addFood(
    @RequestParam("title") String title,
    @RequestParam("file") MultipartFile file,
    @RequestParam("timeShip") String timeShip,
    @RequestParam ("price") Double price,
    @RequestParam("CategoryId") int cateId,
    @RequestParam("ResID") int ResId){
        responeData responeData=new responeData();
     try {
            Category category = categoryRepository.findById(cateId).orElseThrow(()->new RuntimeException("Not found Category Id"+cateId));
            FoodDTO foodDTO= foodService.addFood(title, file, timeShip, price, category,ResId);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            responeData.setData(foodDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    } 

    @PutMapping("/update")
    public ResponseEntity<?> addFood(@RequestParam("id") int id,
    @RequestParam("title") String title,
    @RequestParam(value = "file", required = false) MultipartFile file,
    @RequestParam("timeShip") String timeShip,
    @RequestParam ("price") Double price
    ){
        responeData responeData=new responeData();
     try {
           
            FoodDTO foodDTO= foodService.update(id, title, file, timeShip, price);
            responeData.setStatus(200);
            responeData.setMessage("Success");
            responeData.setData(foodDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( e.getMessage());
        }
    } 

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFood(@RequestParam("id") int id){
        responeData responeData=new responeData();
        try {
            boolean isDelete=foodService.delete(id);
            if(isDelete){
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
