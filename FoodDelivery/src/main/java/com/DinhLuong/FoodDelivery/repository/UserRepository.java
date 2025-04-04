package com.DinhLuong.FoodDelivery.repository;

import com.DinhLuong.FoodDelivery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserName(String userName);
    List<Users> findAll();
    
}
