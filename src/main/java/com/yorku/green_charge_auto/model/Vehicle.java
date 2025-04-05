// Vehicle.java (Model)
package com.yorku.green_charge_auto.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yorku.green_charge_auto.constants.ColorsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid")
    private Integer vid;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "mileage")
    private double mileage;

    @Column(name = "manufactured_year")
    private Integer manufacturedYear;

    @Column(name = "hasBeenInAccident")
    private boolean hasBeenInAccident;

    @Column(name = "body")
    private String body;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image")
    private String image;

    @Column(name = "hot_deal")
    private boolean hotDeal = false;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reviews> reviews = new ArrayList<>();

    @ElementCollection(targetClass = ColorsEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "vehicle_colors", joinColumns = @JoinColumn(name = "vehicle_id"))
    @Column(name = "color")
    private List<ColorsEnum> availableColors;

}
