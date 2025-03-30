package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity(name="Restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="subtitle")
    private String subtitle;
    @Column(name="description")
    private String description;
    @Column(name="image")
    private String image;
    @Column(name = "is_freeship")
    private boolean isFreeship;
    @Column(name = "address")
    private String address;
    @Column(name="open_date")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private Set<Restaurant_Food> ListRes_Food;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> ListRatingRestaurant;

    @OneToMany(mappedBy = "restaurant")
    private Set<Cart> ListCart;
    
    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> ListOrders;

    @OneToMany(mappedBy = "restaurant")
    private Set<MenuRestaurant> ListMenuRestaurant;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> ListPromo;
    
}
