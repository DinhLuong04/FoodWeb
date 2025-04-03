package com.DinhLuong.FoodDelivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Cart;
import com.DinhLuong.FoodDelivery.entity.Cart_Items;
import com.DinhLuong.FoodDelivery.entity.Food;
import com.DinhLuong.FoodDelivery.payload.respone.CartRespone;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Items, Integer> {
     Optional<Cart_Items> findByCartAndFood(Cart cart, Food food);
     Optional<Cart_Items> findByCart(Cart cart);

     @Query("SELECT new com.DinhLuong.FoodDelivery.payload.respone.CartRespone(" +
     "ci.cart_item_id,f.id, f.title,f.image, f.price, ci.quantity, r.id, r.title) " +
     "FROM Cart_Items ci " +
     "JOIN ci.food f " +
     "JOIN ci.cart c " +
     "JOIN c.restaurant r " +
     "WHERE c.users.id = :userId")
List<CartRespone> findCartItemsByUserId(@Param("userId") Integer userId);
}
