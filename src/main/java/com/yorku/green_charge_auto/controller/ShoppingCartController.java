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

    @PostMapping("/create")
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.createCart(shoppingCart);
    }

    @PutMapping("/{id}/add-to-cart")
    public ResponseEntity<ShoppingCart> addToCart(@PathVariable int cartId,@RequestBody Vehicle vehicle) {
        try {
            return ResponseEntity.ok(shoppingCartService.addToCart(cartId, vehicle));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
