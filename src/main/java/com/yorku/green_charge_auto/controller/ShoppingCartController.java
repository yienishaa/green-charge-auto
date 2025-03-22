package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.service.ShoppingCartService;
import com.yorku.green_charge_auto.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private VehicleService vehicleService;


    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable int id) {
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCart(id);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.createCart(shoppingCart);
    }

    @PutMapping("/update")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        try {
            return ResponseEntity.ok(shoppingCartService.updateCart(shoppingCart));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
