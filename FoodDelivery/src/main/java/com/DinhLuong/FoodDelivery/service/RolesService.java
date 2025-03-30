package com.DinhLuong.FoodDelivery.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.dto.RolesDTO;
import com.DinhLuong.FoodDelivery.entity.Roles;
import com.DinhLuong.FoodDelivery.repository.RoleRepository;
import com.DinhLuong.FoodDelivery.service.imp.RolesServiceImp;
@Service
public class RolesService implements RolesServiceImp {
    final Date now = Date.from(Instant.now());
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<RolesDTO> getAll() {
       List<Roles> roles=roleRepository.findAll();
       List<RolesDTO> rolesDTO=new ArrayList<>();
       for(Roles x : roles){
        RolesDTO roleDTO=new RolesDTO(x.getId(), x.getRoleName(), x.getCreateDate());
        rolesDTO.add(roleDTO);
       }
       return rolesDTO;
    }
    @Override
    public RolesDTO getByid(int id) {
        Roles roles=roleRepository.findById(id).orElseThrow(
        ()->new RuntimeException("not found role with id :"+id));
        RolesDTO rolesDTO =new RolesDTO(roles.getId(), roles.getRoleName(), roles.getCreateDate());
        return rolesDTO;
    }
    @Override
    public RolesDTO addRole(String roleName) {
        Roles roles=new Roles();
        roles.setRoleName(roleName);
        roles.setCreateDate(now);
        roleRepository.save(roles);
        RolesDTO rolesDTO=new RolesDTO(roles.getId(), roles.getRoleName(), roles.getCreateDate());
        return rolesDTO;
    }
    @Override
    public RolesDTO update(int id,String roleName) {
        Roles roles=roleRepository.findById(id).orElseThrow(
        ()->new RuntimeException("not found role with id :"+id));
        roles.setRoleName(roleName);
        roleRepository.save(roles);
        RolesDTO rolesDTO=new RolesDTO(roles.getId(), roles.getRoleName(), roles.getCreateDate());
        return rolesDTO;
    }
    @Override
    public boolean Delete(int id) {
        Roles roles=roleRepository.findById(id).orElseThrow(
            ()->new RuntimeException("not found role with id :"+id));
        roleRepository.delete(roles);
        return true;
    }
    
}
