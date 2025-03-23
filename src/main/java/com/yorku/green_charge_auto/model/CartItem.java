package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vehicle vehicle;

    private Integer quantity;

    public CartItem(ShoppingCart shoppingCart, Vehicle vehicle, Integer quantity) {
        this.shoppingCart = shoppingCart;
        this.vehicle = vehicle;
        this.quantity = quantity;
    }
}
