package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vehicle vehicle;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "totalPrice")
    private Double totalPrice;



}
