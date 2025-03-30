package com.DinhLuong.FoodDelivery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.dto.AddressDTO;
import com.DinhLuong.FoodDelivery.entity.Address;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.repository.AddressRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.AddressServiceImp;
@Service
public class AddressService implements AddressServiceImp {

    @Autowired
    UserRepository userRepository;
    @Autowired 
    AddressRepository addressRepository;

    @Override
    public List<AddressDTO> getAll(String username) {
        Users users=userRepository.findByUserName(username).get();
        List<AddressDTO>listDTO=new ArrayList<>();
        for(Address x : users.getListAddress()){
            if(x.getUsers()==users){
                AddressDTO addressDTO=new AddressDTO();
                addressDTO.setId(x.getId());
                addressDTO.setFullAddress(x.getFull_address());
                addressDTO.setCity(x.getCity());
                addressDTO.setDistrict(x.getDistrict());
                addressDTO.setWard(x.getWard());
                addressDTO.setPhone(x.getPhone());
                listDTO.add(addressDTO);
            }
        }
        return listDTO;
    }

    @Override
    public AddressDTO addAddress(String username, String fullAddress, String city, String district, String ward,
            String phone) {
            Users users=userRepository.findByUserName(username).get();
            if(users==null){
                throw new RuntimeException("user not found");
            }
            Address address=new Address();
            address.setUsers(users);
            address.setFull_address(fullAddress);
            address.setCity(city);
            address.setDistrict(district);
            address.setWard(ward);
            address.setPhone(phone);
            addressRepository.save(address);
            AddressDTO addressDTO=new AddressDTO(address.getId(), fullAddress, city, district, ward, phone);
            return addressDTO;
    }

    @Override
    public AddressDTO UpdateAddress( int AddressID, String fullAddress, String city, String district,
            String ward, String phone) {
                Address address=addressRepository.findById(AddressID)
                .orElseThrow(()->new RuntimeException("Not found Address with ID: " + AddressID) );
                address.setFull_address(fullAddress);
                address.setCity(city);
                address.setDistrict(district);
                address.setWard(ward);
                address.setPhone(phone);
                addressRepository.save(address);
                AddressDTO addressDTO=new AddressDTO(AddressID, fullAddress, city, district, ward, phone);
                return addressDTO;

    }

    @Override
    public Boolean DeleteAddress(int AddressID) {
       Address address=addressRepository.findById(AddressID)
       .orElseThrow(()->new RuntimeException("Not found Address with ID: " + AddressID) );
       addressRepository.delete(address);
       return true;

    }
    
}
