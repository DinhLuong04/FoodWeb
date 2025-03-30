package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity(name="Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name="name_cate")
    private  String nameCate ;
    @Column(name="create_date")
    private Date creatDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> foods;

    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurant> ListMenuRestaurant;

}
