package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.dto.CartItemResponse;
import com.yorku.green_charge_auto.model.CartItem;
import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public List<CartItemResponse> getCartItemsByCartId(@PathVariable int id) {
        return shoppingCartService.getCartItemsByCartId(id);
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

    @DeleteMapping("/{id}/delete-all")
    public HttpStatus deleteShoppingCart(@PathVariable int id) {
        try {
            if(shoppingCartService.deleteCart(id)){
                return HttpStatus.OK;
            }else {
                return HttpStatus.NOT_FOUND;
            }

        }catch (RuntimeException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PutMapping("/update-cart")
    public HttpStatus updateCart(@RequestBody CartItem cartItem) {
        int cartId = cartItem.getShoppingCart().getCartId();
        shoppingCartService.removeFromCart(cartId, cartItem.getVehicle());
        return HttpStatus.OK;
    }

}
