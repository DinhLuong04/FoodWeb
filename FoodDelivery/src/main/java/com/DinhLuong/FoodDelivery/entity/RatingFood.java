package com.DinhLuong.FoodDelivery.entity;



import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="RatingFood")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;
    @Column(name="content")
    private String content;
    @Column(name="rate_point")
    private int ratePoint;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "image")
    private String image;
    
}
