package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid")
    private Integer vid;

    @Column(name = "name") //Toyota, Ford
    private String name;

    @Column(name = "description") //Corolla
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model") //SUV, Truck, Sedan
    private String model;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

}
