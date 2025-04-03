package com.DinhLuong.FoodDelivery.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DinhLuong.FoodDelivery.dto.AddressDTO;
import com.DinhLuong.FoodDelivery.dto.OrderDetailDTO;
import com.DinhLuong.FoodDelivery.dto.Enum.OrderStatus;
import com.DinhLuong.FoodDelivery.entity.Address;
import com.DinhLuong.FoodDelivery.entity.Food;
import com.DinhLuong.FoodDelivery.entity.OrderDetail;
import com.DinhLuong.FoodDelivery.entity.Orders;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.entity.keys.KeyOrderDetail;
import com.DinhLuong.FoodDelivery.payload.request.CreateOrder;
import com.DinhLuong.FoodDelivery.payload.request.OrdersItemRequest;
import com.DinhLuong.FoodDelivery.payload.respone.OrderItemRepone;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;
import com.DinhLuong.FoodDelivery.repository.AddressRepository;
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

    @Autowired
    AddressRepository addressRepository;

    @Transactional 
    @Override
    public boolean CreatOrder(CreateOrder createOrder,String username) {
        try {
            Users user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new RuntimeException("Not found UserName: "+ username ));
            Restaurant res = restaurantRepository.findById(createOrder.getResid())
                    .orElseThrow(() -> new RuntimeException("Not found ResID: " + createOrder.getResid()));
            Address address = addressRepository.findById(createOrder.getAddressid())
                    .orElseThrow(() -> new RuntimeException("Not found Address"));

            Orders orders = new Orders();
            orders.setUsers(user);
            orders.setRestaurant(res);
            orders.setAddress(address);
            orders.setStatus(OrderStatus.PENDING.getValue());
            orders.setCreateDate(Date.from(Instant.now()));

            orders = orderRepository.save(orders);

            double totalPrice = 0;
            Set<OrderDetail> ListorderDetails = new HashSet<>();

           
            for (OrdersItemRequest item : createOrder.getListFood()) {
                Food food = foodRepository.findById(item.getFoodid())
                        .orElseThrow(() -> new RuntimeException("Food not found with ID: " + item.getFoodid()));

                totalPrice += food.getPrice() * item.getQuantity();

                OrderDetail orderDetail = new OrderDetail();
                KeyOrderDetail key = new KeyOrderDetail(orders.getId(), food.getId());
                orderDetail.setKeys(key);
                orderDetail.setOrders(orders);
                orderDetail.setFood(food);
                orderDetail.setCreateDate(Date.from(Instant.now()));
                orderDetail.setQuantity(item.getQuantity());
                ListorderDetails.add(orderDetail);
            }

            
            orders.setTotal_price(totalPrice);
            orderRepository.save(orders); 
            orderDetailRepository.saveAll(ListorderDetails);

        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
        return true;
    }

    @Override
    public List<OrdersRepone> getListOrder(String username) {
        Users users=userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("Not found Users"));
        List<Object[]> results=orderRepository.getOrdersByUserId(users.getId());
        Map<Integer,OrdersRepone> orderMap = new  LinkedHashMap<>();
        for(Object [] row : results){
            Integer orderId= (Integer) row[0];
            OrdersRepone order= orderMap.computeIfAbsent(orderId,id->{
                OrdersRepone newOrder = new OrdersRepone();
                newOrder.setOrderId(id);
                newOrder.setCreateDate((Date) row[1]);
                newOrder.setTotalPrice((Double) row[2]);
                newOrder.setStatus((String) row[3]);
                newOrder.setResName((String) row[4]);
                AddressDTO address = new AddressDTO();
                address.setFullAddress((String) row[5]);
                address.setCity((String) row[6]);
                address.setDistrict((String) row[7]);
                address.setWard((String) row[8]);
                address.setPhone((String) row[9]);
                newOrder.setAddress(address);
                newOrder.setItems(new ArrayList<>());
                return newOrder;
            }
            );
            OrderItemRepone item = new OrderItemRepone();
            item.setId((Integer) row[10]);
            item.setFoodName((String) row[11]);
            item.setQuantity((Integer) row[12]);
            item.setPrice((Double) row[13]);
            order.getItems().add(item);
        }
        List<OrdersRepone> data=new ArrayList<>(orderMap.values());
        return  data;
    }

    @Override
    public OrdersRepone getOrder(int OrderId,String username) {
        Orders orders=orderRepository.findOrderWithDetails(OrderId).orElseThrow(()->new RuntimeException("Not found orders id"+OrderId));
        if (!orders.getUsers().getUserName().equals(username)) {
            throw new RuntimeException("You are not authorized to access this order");
        }
        OrdersRepone ordersRepone=new OrdersRepone();
        ordersRepone.setOrderId(orders.getId());
        ordersRepone.setCreateDate(orders.getCreateDate());
        ordersRepone.setTotalPrice(orders.getTotal_price());
        ordersRepone.setStatus(orders.getStatus());
        Restaurant res=orders.getRestaurant();
        ordersRepone.setResName(res.getTitle());
        AddressDTO addressDTO=new AddressDTO();
        Address  address= orders.getAddress();
        addressDTO.setId(address.getId());
        addressDTO.setFullAddress(address.getFull_address());
        addressDTO.setCity(address.getCity());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setWard(address.getWard());
        addressDTO.setPhone(address.getPhone());
        ordersRepone.setAddress(addressDTO);
        List<OrderItemRepone> items=new ArrayList<>();
        for(OrderDetail x :orders.getListorderDetails()){
            OrderItemRepone item=new OrderItemRepone();
            item.setId(x.getFood().getId());
            item.setFoodName(x.getFood().getTitle());
            item.setPrice(x.getFood().getPrice());
            item.setQuantity(x.getQuantity());
            items.add(item);
        }
        ordersRepone.setItems(items);
        return ordersRepone;
    }
   
}
