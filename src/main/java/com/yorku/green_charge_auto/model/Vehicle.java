// Vehicle.java (Model)
package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid")
    private Integer vid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    public Vehicle() {}

    public Vehicle(String name, String description, String brand, String model, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Integer getVid() { return vid; }
    public void setVid(Integer vid) { this.vid = vid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
