package com.DinhLuong.FoodDelivery.entity;

import com.DinhLuong.FoodDelivery.entity.keys.KeyMenuRestaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="MenuRestaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuRestaurant {
    @EmbeddedId
    KeyMenuRestaurant keys;
    @ManyToOne
    @JoinColumn(name="cate_id" ,insertable = false, updatable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name="res_id",insertable = false, updatable = false)
    private Restaurant restaurant;
    @Column(name="create_date")
    private Date createDate;

}
