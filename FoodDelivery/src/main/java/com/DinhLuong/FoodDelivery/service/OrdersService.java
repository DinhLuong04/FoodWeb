package com.DinhLuong.FoodDelivery.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.dto.OrderDetailDTO;
import com.DinhLuong.FoodDelivery.entity.Food;
import com.DinhLuong.FoodDelivery.entity.OrderDetail;
import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.entity.keys.KeyOrderDetail;
import com.DinhLuong.FoodDelivery.payload.request.CreateOrder;
import com.DinhLuong.FoodDelivery.repository.FoodRepository;
import com.DinhLuong.FoodDelivery.repository.OrderDetailRepository;
import com.DinhLuong.FoodDelivery.repository.OrderRepository;
import com.DinhLuong.FoodDelivery.repository.RestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.OrdersServiceImp;
@Service
public class OrdersService implements OrdersServiceImp{
    final Date now = Date.from(Instant.now());
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public boolean CreatOrder(CreateOrder createOrder) {
        
        try {
            Users user=userRepository.findById(createOrder.getUserid()).orElseThrow(()-> new RuntimeException("Not found User ID:"+createOrder.getUserid()));
            Restaurant res=restaurantRepository.findById(createOrder.getResid()).orElseThrow(()-> new RuntimeException("Not found ResID:"+createOrder.getResid()));

            Orders orders=new Orders();
            orders.setUsers(user);
            orders.setRestaurant(res);
            orders.setCreateDate(now);
            orderRepository.save(orders);
            
            //orderDetail
            Set<OrderDetail> ListorderDetails = new HashSet<>();
            for(int item : createOrder.getFoodid()){
                OrderDetail orderDetail=new OrderDetail();
                Food food=foodRepository.findById(item).get();
                KeyOrderDetail key=new KeyOrderDetail(orders.getId(),food.getId());
                orderDetail.setKeys(key);
                orderDetail.setOrders(orders);
                orderDetail.setFood(food);
                orderDetail.setCreateDate(now);
                ListorderDetails.add(orderDetail);
            }
            orderDetailRepository.saveAll(ListorderDetails);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
