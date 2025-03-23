package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.CartItem;
import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.CartItemRepository;
import com.yorku.green_charge_auto.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Optional<ShoppingCart> getShoppingCart(int shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId);
    }

    private ShoppingCart getShoppingCartAfterAddItem(int shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElse(null);
    }

    public ShoppingCart createCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteCart(int shoppingCartId) {
        shoppingCartRepository.deleteById(shoppingCartId);
    }

    public ShoppingCart addToCart(int cartId, Vehicle vehicle) {
        shoppingCartRepository.findById(cartId).ifPresent(shoppingCart -> {
            cartItemRepository.save(new CartItem(shoppingCart, vehicle,1));
        });
        return getShoppingCartAfterAddItem(cartId);
    }

    public ShoppingCart removeFromCart(int cartId, Vehicle vehicle) {
        shoppingCartRepository.findById(cartId).ifPresent(shoppingCart -> {
            cartItemRepository.findById(cartId).ifPresent(cartItem -> {
                int quantity = cartItem.getQuantity();
                if (quantity > 0) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                }
                else {
                    cartItemRepository.delete(cartItem);
                }
            });
        });
        return getShoppingCartAfterAddItem(cartId);
    }




}
