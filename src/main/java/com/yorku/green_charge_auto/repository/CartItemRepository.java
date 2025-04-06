package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.CartItem;
import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByShoppingCart_CartId(Integer cart_id);

    Optional<CartItem> findByShoppingCartAndVehicle(ShoppingCart cart, Vehicle vehicle);

    List<CartItem> findByShoppingCart(ShoppingCart cart);


}
