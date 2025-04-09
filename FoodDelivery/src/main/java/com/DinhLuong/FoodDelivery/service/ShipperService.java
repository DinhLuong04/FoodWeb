package com.DinhLuong.FoodDelivery.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.dto.Mapper.OrderMapper;
import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.entity.Roles;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.payload.request.SignUpRequest;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;
import com.DinhLuong.FoodDelivery.repository.OrderRepository;
import com.DinhLuong.FoodDelivery.repository.RoleRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.ShipperServiceImp;
@Service
public class ShipperService implements ShipperServiceImp {

    @Autowired 
    UserRepository userrepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<OrdersRepone> getOrderForShipper(String UserName) {
        Users user=userrepository.findByUserName(UserName).orElseThrow(()-> new RuntimeException("not found"));
        List<Orders> list=orderRepository.findAvailableOrdersForShippers();
        return orderMapper.toOrdersReponeList(list);
    }
    @Override
    public String register(SignUpRequest signUpRequest) {
        
            if (userrepository.findByUserName(signUpRequest.getEmail()).isPresent()) {
                throw new RuntimeException("Username already exists");
            }
    
            Date now = Date.from(Instant.now());;
            Roles roleShipper = roleRepository.findByRoleName("ROLE_SHIPPER")
            .orElseGet(() -> {
                Roles newRole = new Roles();
                newRole.setRoleName("ROLE_SHIPPER");
                roleRepository.save(newRole); 
                return newRole; 
            });
            Users user = new Users();
            user.setFullName(signUpRequest.getFullName());
            user.setUserName(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setCreateDate(now);
            user.setRoles(roleShipper);
            return null;

    
        }
    
    
}
