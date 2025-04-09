package com.DinhLuong.FoodDelivery.dto.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;
@Mapper(componentModel = "spring" ,uses = { AddressMapper.class, OrderItemMapper.class }) 
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "restaurant.title", target = "resName", defaultValue = "N/A")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "total_price", target = "totalPrice")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "listorderDetails", target = "items")
    OrdersRepone toOrdersRepone(Orders order);

    List<OrdersRepone> toOrdersReponeList(List<Orders> orders);
}
