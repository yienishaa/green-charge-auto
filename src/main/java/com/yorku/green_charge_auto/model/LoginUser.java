package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loginUser")
public class LoginUser {

    @Id //To mention this is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginUserId")
    private Integer userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "addressId")
    private Address address;

}
