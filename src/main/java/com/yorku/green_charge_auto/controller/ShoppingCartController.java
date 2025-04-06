package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.dto.AddToCartRequest;
import com.yorku.green_charge_auto.dto.CartItemResponse;
import com.yorku.green_charge_auto.dto.UpdateCartRequest;
import com.yorku.green_charge_auto.repository.CartItemRepository;
import com.yorku.green_charge_auto.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/get-cart")
    public List<CartItemResponse> getCartItemsByUserId(@RequestBody Map<String, String>body) {
        int userId = Integer.parseInt(body.get("userId"));
        return shoppingCartService.getCartItemsByUserId(userId);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        shoppingCartService.addToCart(request.getVid(), request.getQuantity(), request.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/delete-all")
    public HttpStatus deleteShoppingCart(@PathVariable int userId) {
        try {
            if(shoppingCartService.deleteCart(userId)){
                return HttpStatus.OK;
            }else {
                return HttpStatus.NOT_FOUND;
            }

        }catch (RuntimeException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/{id}/delete-item")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        return shoppingCartService.removeFromCart(id);
    }

    @PutMapping("/update-cart")
    public ResponseEntity<String> updateCart(@RequestBody UpdateCartRequest request) {
        return shoppingCartService.updateCartItem(request);
    }

}
