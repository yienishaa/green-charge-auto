package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    void updateCart(ShoppingCart shoppingCart);
}
