package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.CartItemResponse;
import com.yorku.green_charge_auto.model.CartItem;
import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.CartItemRepository;
import com.yorku.green_charge_auto.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemResponse> getCartItemsByCartId(int shoppingCartId) {
        List<CartItem> cartItemList = cartItemRepository.findByShoppingCart_CartId(shoppingCartId);

        List<CartItemResponse> cartItemResponseList = new ArrayList<>();

        for (CartItem cartItem : cartItemList) {
            cartItemResponseList.add(toCartItemResponse(cartItem));
        }
        return cartItemResponseList;
    }

    private ShoppingCart getShoppingCartAfterAddItem(int shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId).orElse(null);
    }

    public ShoppingCart createCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public boolean deleteCart(int shoppingCartId) {
        if (shoppingCartRepository.findById(shoppingCartId).isPresent()) {
            shoppingCartRepository.deleteById(shoppingCartId);
            return true;
        }else {
            return false;
        }

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

    public CartItemResponse toCartItemResponse(CartItem cartItem) {

        CartItemResponse cartItemResponse = new CartItemResponse();
        String vehicleName = cartItem.getVehicle().getBrand()+" "+cartItem.getVehicle().getModel();
        cartItemResponse.setId(cartItem.getCartItemId());
        cartItemResponse.setVehicleName(vehicleName);
        cartItemResponse.setPrice(cartItem.getVehicle().getPrice());
        cartItemResponse.setImageUrl(cartItem.getVehicle().getImage());
        cartItemResponse.setQuantity(cartItem.getQuantity());

        return cartItemResponse;
    }


}
