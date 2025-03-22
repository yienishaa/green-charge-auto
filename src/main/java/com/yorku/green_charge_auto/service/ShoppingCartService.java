package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public Optional<ShoppingCart> getShoppingCart(int shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId);
    }

    public void saveCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    public void deleteCart(int shoppingCartId) {
        shoppingCartRepository.deleteById(shoppingCartId);
    }

    public ShoppingCart updateCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.updateCart(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart createCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }


}
