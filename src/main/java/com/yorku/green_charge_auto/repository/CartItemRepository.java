package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByShoppingCart_CartId(Integer cart_id);

}
