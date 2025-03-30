package com.DinhLuong.FoodDelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.UsersServiceImp;
import com.DinhLuong.FoodDelivery.dto.UserDTO;
import com.DinhLuong.FoodDelivery.entity.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UsersServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ImgBBService imgBBService;

    public String checkAndUploadImage(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Mã hóa ảnh mới sang Base64
                String newImageBase64 = Base64.getEncoder().encodeToString(file.getBytes());

                // Kiểm tra ảnh đã tồn tại trên ImgBB chưa
                String existingImageUrl = imgBBService.findImageOnImgBB(newImageBase64);
                
                if (existingImageUrl != null) {
                    return existingImageUrl; 
                } else {
                    return imgBBService.upLoadImage(file); 
                }
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi đọc ảnh: alo " + e.getMessage());
            }
        }
        return null; 
    }

    // Admin
    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        List<UserDTO> list = new ArrayList<>() {

        };

        for (Users user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUserName(), user.getPassword(), user.getFullName(),
                    user.getCreateDate(), user.getAvatar());
            list.add(userDTO);
        }
        return list;
    }

    // Users,Shipper,Admin
    @PreAuthorize("#username == authentication.name") // Chặn ngay từ đầu
    @Override
    public UserDTO getMyInfor(String username) {
        Users users = userRepository.findByUserName(username).get();
        if (users == null) {
            throw new RuntimeException("User not found");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setUserName(users.getUserName());
        userDTO.setFullName(users.getFullName());
        userDTO.setAvatar(users.getAvatar());

        return userDTO;
    }

    @PreAuthorize("#username == authentication.name")
    @Override
    public UserDTO UpdateInfor(String username,String fullname, String password, MultipartFile file) {
        Users users=userRepository.findByUserName(username).get();
        if(users==null){
            throw new RuntimeException("not found user");
        }
        users.setFullName(fullname);
        users.setPassword(passwordEncoder.encode(password));
        String imageUrl = checkAndUploadImage(file);
        if (imageUrl != null) {
            users.setAvatar(imageUrl);
        }
        userRepository.save(users);
        UserDTO userDTO=new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setUserName(users.getUserName());
        userDTO.setFullName(users.getFullName());
        userDTO.setPassword(users.getPassword());
        userDTO.setCreateDate(users.getCreateDate());
        userDTO.setAvatar(users.getAvatar());
        return userDTO;
    }

    


    

}
