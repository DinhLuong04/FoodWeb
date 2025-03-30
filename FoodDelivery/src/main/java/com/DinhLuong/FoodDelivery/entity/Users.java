package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="fullname")
    private String fullName;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name="avatar")
    private String avatar;
    
    //Chứa khóa ngoại thì JoinColumn
    @ManyToOne
    @JoinColumn(name="role_id")
//    sau khi thêm role thì xóa role_id
    private Roles roles;

//    private int role_id;
    @OneToMany(mappedBy = "usersReceiver")
    private Set<ChatMessages> ListChatMessageReceiver;
    
    @OneToMany(mappedBy = "usersSender")
    private Set<ChatMessages> ListChatMessage;

    @OneToMany(mappedBy = "users")
    private Set<Delivery> ListDelivery;

    @OneToMany(mappedBy = "users")
    private Set<Payment> ListPayment;
   
    @OneToMany(mappedBy = "users")
    private Set<Favorites> listFavorites;
    
    @OneToMany(mappedBy = "users")
    private Set<Address> ListAddress;
   
    @OneToMany(mappedBy = "users")
    private Set<RatingFood> ListRatingFoods ;

    @OneToMany(mappedBy ="users")
    private Set<Notifications> ListNotifications;

    @OneToMany(mappedBy = "users")
    private Set<Cart> ListCart;
    
    @OneToMany(mappedBy = "users")
    private Set<RatingRestaurant> ListRatingRestaurants;

    @OneToMany(mappedBy = "users")
    private Set<Orders> ListOrders;

    
}
