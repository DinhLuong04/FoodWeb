package com.DinhLuong.FoodDelivery.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;
    @Column(name="message")
    private String message;
    @Column(name="is_read")
    private boolean is_read;
    @Column(name="created_at")
    private Date created_at;
   
}
