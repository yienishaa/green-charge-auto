package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable int id) {
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCart(id);
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        }
        return ResponseEntity.notFound().build();
    }

}
