package com.DinhLuong.FoodDelivery.entity;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name="res_id")
    private Restaurant restaurant;
    @Column(name = "created_at")
    private Date created_at;
    
    @OneToMany(mappedBy = "cart")
    private Set<Cart_Items> ListCartItems;

    
    
}