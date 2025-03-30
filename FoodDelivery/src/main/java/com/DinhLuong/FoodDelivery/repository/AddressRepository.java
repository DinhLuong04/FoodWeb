package com.DinhLuong.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{
   @Query(value = "select * from Address where user_id=?;",nativeQuery=true)
   List<Address> findbyUsersId(int id);
}
