package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity(name="Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name="res_id")
    private Restaurant restaurant;
    @Column(name="create_date")
    private Date createDate;
    @Column(name = "total_price")
    private double total_price;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "orders")
    private Set<Delivery> ListDelivery;

    @OneToMany(mappedBy = "orders")
    private Set<Payment> ListPayment;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetail> ListorderDetails;
    
}
