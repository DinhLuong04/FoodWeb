package com.DinhLuong.FoodDelivery.repository;
import com.DinhLuong.FoodDelivery.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(String roleName);
}
