package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import com.DinhLuong.FoodDelivery.dto.RolesDTO;

public interface RolesServiceImp {
    List<RolesDTO> getAll();
    RolesDTO getByid(int id);
    RolesDTO addRole(String roleName);
    RolesDTO update(int id,String roleName);
    boolean  Delete(int id);
}
