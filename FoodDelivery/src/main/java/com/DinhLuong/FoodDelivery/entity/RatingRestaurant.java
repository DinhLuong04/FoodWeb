package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="RatingRestaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name="res_id")
    private Restaurant restaurant;
    @Column(name="content")
    private String content;
    @Column(name="rate_point")
    private int ratePoint;

}
