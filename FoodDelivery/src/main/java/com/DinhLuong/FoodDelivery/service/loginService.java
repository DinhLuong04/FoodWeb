package com.DinhLuong.FoodDelivery.service;

import com.DinhLuong.FoodDelivery.dto.UserDTO;
import com.DinhLuong.FoodDelivery.entity.Roles;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.payload.request.SignUpRequest;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.loginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class loginService  implements loginServiceImp {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public boolean CheckLogin(String username, String password) {
        Optional<Users> userOptional = userRepository.findByUserName(username);
       
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            System.out.println(user.getFullName());
            return passwordEncoder.matches(password, user.getPassword());  // ✅ So sánh mật khẩu đã mã hóa
        }
        return false;

    }

    @Override
    public boolean RegisterUser(SignUpRequest signUpRequest) {
        if (userRepository.findByUserName(signUpRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Date now = Date.from(Instant.now());;
        Roles roles = new Roles();
        roles.setId(1);
        Users user = new Users();
        user.setFullName(signUpRequest.getFullName());
        user.setUserName(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setCreateDate(now);
        user.setRoles(roles);

        try{
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public Users findbyEmail (String email) {
        Optional<Users> userOptional = userRepository.findByUserName(email);
        if (userOptional.isPresent())
        {
            Users user = userOptional.get();
            return user;
        }
        return null;
       
    }

   
}
