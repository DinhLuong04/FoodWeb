package com.DinhLuong.FoodDelivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Entity(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="role_name")
    private String roleName;
    @Column(name="create_date")
    private Date createDate;
//Map ngược lại Users//mappedBy
    @OneToMany(mappedBy = "roles")
    private Set<Users> ListUser;
 
}
