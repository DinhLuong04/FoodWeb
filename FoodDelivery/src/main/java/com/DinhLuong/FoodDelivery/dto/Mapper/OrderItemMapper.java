package com.DinhLuong.FoodDelivery.dto.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.DinhLuong.FoodDelivery.entity.OrderDetail;
import com.DinhLuong.FoodDelivery.payload.respone.OrderItemRepone;
@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(source = "food.id", target = "id")
    @Mapping(source = "food.title", target = "foodName") 
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "food.price", target = "price")
    OrderItemRepone toOrderItemRepone(OrderDetail orderDetail);

    List<OrderItemRepone> toOrderItemReponeList(List<OrderDetail> orderDetails);
}
