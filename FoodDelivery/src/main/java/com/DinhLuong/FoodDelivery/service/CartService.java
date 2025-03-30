package com.DinhLuong.FoodDelivery.service;
import jakarta.transaction.Transactional;
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
    @Transactional
    public boolean createCart(CreateCart createCart) {
        Users users=userRepository.findById(createCart.getUser_id()).orElseThrow(()->new RuntimeException(" User not found") );
        Restaurant res=restaurantRepository.findById(createCart.getRes_id()).orElseThrow(()-> new RuntimeException("Res not found") );
        Cart cart = cartRepository.findByUsersAndRestaurant(users, res)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUsers(users);
                    newCart.setRestaurant(res);
                    newCart.setCreated_at(new Date());
                    return cartRepository.save(newCart);
                });
        Food food=foodRepository.findById(createCart.getFood_id()).orElseThrow(()-> new RuntimeException(" food not found"));
        Cart_Items cartItem = cartItemRepository.findByCartAndFood(cart, food)
        .orElseGet(() -> {
            Cart_Items newItem = new Cart_Items();
            newItem.setCart(cart);
            newItem.setFood(food);
            newItem.setQuantity(0);
            return newItem;
        });
         // Cập nhật số lượng món ăn
        cartItem.setQuantity(cartItem.getQuantity() + createCart.getQuality());
        cartItemRepository.save(cartItem);
        return true;
    }

    
    
}
