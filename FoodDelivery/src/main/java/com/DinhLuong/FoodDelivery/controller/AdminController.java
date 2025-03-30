package com.DinhLuong.FoodDelivery.controller;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.DinhLuong.FoodDelivery.dto.CategoryDTO;
import com.DinhLuong.FoodDelivery.dto.RolesDTO;
import com.DinhLuong.FoodDelivery.dto.UserDTO;
import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.service.CategoryService;
import com.DinhLuong.FoodDelivery.service.RolesService;
import com.DinhLuong.FoodDelivery.service.UserService;

@RestController
@RequestMapping("admin/")
public class AdminController {

    @Autowired
    RolesService rolesService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    //Users manage
    @GetMapping("/users/getAll")
    public ResponseEntity<?> getAllUsers() {
        responeData responeData = new responeData();
        try{
            List<UserDTO> users = userService.getAllUsers();
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(users);
        }
        catch(Exception e){
            responeData.setStatus(400);
            responeData.setMessage(e.getMessage());
            return new ResponseEntity<>( responeData, HttpStatus.OK);
        }
        return new ResponseEntity<>( responeData, HttpStatus.OK);
    }
    //Roles manage
    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getRoles(){
        responeData responeData=new responeData();
        try {
            List<RolesDTO> list=rolesService.getAll();
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(list);
            return ResponseEntity.ok(responeData);
        } 
        catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @GetMapping("/getRole")
    public ResponseEntity<?> getRole(@RequestParam("id") int id){
        responeData responeData=new responeData();
        try {
            RolesDTO rolesDTO=rolesService.getByid(id);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(rolesDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestParam("RoleName") String RoleName)
    {
        responeData responeData=new responeData();
        try {
            RolesDTO rolesDTO=rolesService.addRole(RoleName);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(rolesDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @PutMapping("/updateRole")
    public ResponseEntity<?> update(
    @RequestParam("id") int id,
    @RequestParam("roleName" )String roleName)
    {
        responeData responeData=new responeData();
        try {
            RolesDTO rolesDTO=rolesService.update(id,roleName);
            responeData.setStatus(200);
            responeData.setMessage("success");
            responeData.setData(rolesDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @DeleteMapping("/deleteRole")
    public ResponseEntity<?> deleteRole(@RequestParam("id") int id)
    {
        responeData responeData=new responeData();
        try {
            boolean isDelete=rolesService.Delete(id);
            if(isDelete){
                responeData.setStatus(200);
                responeData.setMessage("success");
                return ResponseEntity.ok(responeData);
            }
            else{
                responeData.setStatus(404);
                responeData.setMessage("Not found");
                return ResponseEntity.status(404).body(responeData);
            }
           
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    //Category manage
    @GetMapping("/Category/getAll")
    public ResponseEntity<?> getAll(){
        responeData responeData=new responeData();
        try {
            List<CategoryDTO> list=categoryService.getAll();
            responeData.setStatus(200);
            responeData.setMessage("200");
            responeData.setData(list);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @GetMapping("/Category/getCategory")
    public ResponseEntity<?> getCateById(@RequestParam("id")  int id){
        responeData responeData=new responeData();
        try {
            CategoryDTO categoryDTO=categoryService.getCate(id);
            responeData.setStatus(200);
            responeData.setMessage("200");
            responeData.setData(categoryDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @PostMapping("/Category/add")
    public ResponseEntity<?> addCategory(@RequestParam("Name")  String name){
        responeData responeData=new responeData();
        try {
            CategoryDTO categoryDTO=categoryService.addCate(name);
            responeData.setStatus(200);
            responeData.setMessage("200");
            responeData.setData(categoryDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @PutMapping("/Category/update")
    public ResponseEntity<?> updateCategory(@RequestParam("id") int id,@RequestParam("Name")  String name){
        responeData responeData=new responeData();
        try {
            CategoryDTO categoryDTO=categoryService.update(id, name);
            responeData.setStatus(200);
            responeData.setMessage("200");
            responeData.setData(categoryDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            responeData.setStatus(500);
            responeData.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(responeData);
        }
    }

    @DeleteMapping("/Category/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam("id") int id){
        responeData responeData=new responeData();
        try {
            boolean isDelete=categoryService.DeleteCate(id);
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
