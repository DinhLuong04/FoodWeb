package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name="Food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="image")
    private String image;
    @Column(name="time_ship")
    private String timeShip;
    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;

    @OneToMany(mappedBy = "food")
    private Set<Restaurant_Food> ListRes_Food;

    @OneToMany(mappedBy = "food")
    private Set<Favorites> listFavorites;
   
    @OneToMany(mappedBy = "food")
    private Set<RatingFood> ListRatingfood;
   
    @OneToMany(mappedBy = "food")
    private Set<OrderDetail> ListOrderDetail;

    @OneToMany(mappedBy = "food")
    private Set<Cart_Items> ListCartItems;
   
}
