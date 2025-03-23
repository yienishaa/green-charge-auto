package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.CartItem;
import com.yorku.green_charge_auto.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}
