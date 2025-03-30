package com.DinhLuong.FoodDelivery.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name = "Cart_Items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_item_id;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;
    @Column(name="quantity")
    private int quantity;
    
}
