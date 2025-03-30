package com.DinhLuong.FoodDelivery.entity;


import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "payment_method")
    private String payment_method;
    @Column(name = "payment_status")
    private String payment_status;
    @Column(name = "payment_date")
    private Date payment_date;

}
