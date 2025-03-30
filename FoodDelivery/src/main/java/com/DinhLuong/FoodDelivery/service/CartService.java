package com.DinhLuong.FoodDelivery.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.entity.Cart;
import com.DinhLuong.FoodDelivery.entity.Cart_Items;
import com.DinhLuong.FoodDelivery.entity.Food;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.Users;
import com.DinhLuong.FoodDelivery.payload.request.CreateCart;
import com.DinhLuong.FoodDelivery.repository.CartItemRepository;
import com.DinhLuong.FoodDelivery.repository.CartRepository;
import com.DinhLuong.FoodDelivery.repository.FoodRepository;
import com.DinhLuong.FoodDelivery.repository.RestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.imp.CartServiceImp;
@Service
public class CartService implements CartServiceImp {
    final Date now = Date.from(Instant.now());
    @Autowired 
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired 
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    FoodRepository foodRepository;
    @Override
    public boolean createCart(CreateCart createCart) {
        Users users=userRepository.findById(createCart.getUser_id()).orElseThrow(()->new RuntimeException(" User not found") );
        Restaurant res=restaurantRepository.findById(createCart.getRes_id()).orElseThrow(()-> new RuntimeException("Res not found") );
        Cart cart=new Cart();
        cart.setUsers(users);
        cart.setRestaurant(res);
        cart.setCreated_at(now);
        cartRepository.save(cart);
        Food food=foodRepository.findById(createCart.getFood_id()).orElseThrow(()-> new RuntimeException(" food not found"));
        Cart_Items cart_Items=new Cart_Items();
        cart_Items.setCart(cart);
        cart_Items.setFood(food);
        cart_Items.setQuantity(createCart.getQuality());
        cartItemRepository.save(cart_Items);
        return true;
    }

    
    
}
