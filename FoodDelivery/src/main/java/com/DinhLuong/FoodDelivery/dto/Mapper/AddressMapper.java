package com.DinhLuong.FoodDelivery.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.DinhLuong.FoodDelivery.dto.AddressDTO;
import com.DinhLuong.FoodDelivery.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "full_address", target = "fullAddress")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "district", target = "district")
    @Mapping(source = "ward", target = "ward")
    @Mapping(source = "phone", target = "phone")
    AddressDTO toAddressDTO(Address address);
}
