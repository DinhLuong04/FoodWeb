package com.DinhLuong.FoodDelivery.entity;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name = "Delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
    @Column(name = "delivery_time")
    private Date delivery_time;
    @Column(name = "delivery_status")
    private String delivery_status;
    @Column(name = "confirmation_time")
    private Date confirmation_time;
    @Column(name = "delivery_fee")
    private Double delivery_fee;
    @Column(name = "delivery_person")
    private String delivery_person;
    @Column(name = "phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Users users;
    
}
