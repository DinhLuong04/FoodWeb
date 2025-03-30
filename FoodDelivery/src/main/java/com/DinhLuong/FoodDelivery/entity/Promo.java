package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="Promo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="res_id")
    private Restaurant restaurant;
    @Column(name="percent")
    private int percent;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;

}
