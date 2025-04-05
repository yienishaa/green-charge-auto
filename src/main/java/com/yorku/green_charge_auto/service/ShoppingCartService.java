package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.dto.CartItemResponse;
import com.yorku.green_charge_auto.dto.UpdateCartRequest;
import com.yorku.green_charge_auto.model.CartItem;
import com.yorku.green_charge_auto.model.LoginUser;
import com.yorku.green_charge_auto.model.ShoppingCart;
import com.yorku.green_charge_auto.model.Vehicle;
import com.yorku.green_charge_auto.repository.CartItemRepository;
import com.yorku.green_charge_auto.repository.LoginUserRepository;
import com.yorku.green_charge_auto.repository.ShoppingCartRepository;
import com.yorku.green_charge_auto.repository.VehicleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<CartItemResponse> getCartItemsByCartId(int shoppingCartId) {
        List<CartItem> cartItemList = cartItemRepository.findByShoppingCart_CartId(shoppingCartId);

        List<CartItemResponse> cartItemResponseList = new ArrayList<>();

        for (CartItem cartItem : cartItemList) {
            cartItemResponseList.add(toCartItemResponse(cartItem));
        }
        return cartItemResponseList;
    }

    public boolean deleteCart(int shoppingCartId) {
        if (shoppingCartRepository.findById(shoppingCartId).isPresent()) {
            shoppingCartRepository.deleteById(shoppingCartId);
            return true;
        }else {
            return false;
        }

    }

    public ShoppingCart addToCart(int vid, int quantity, int userId) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByLoginUser_Id(userId);

        ShoppingCart cart = optionalCart.orElseGet(()->{
            ShoppingCart shoppingCart = new ShoppingCart();
            LoginUser loginUser = loginUserRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
            shoppingCart.setLoginUser(loginUser);
            return shoppingCartRepository.save(shoppingCart);
        });

        //Get the vehicle
        Vehicle vehicle = vehicleRepository.findById(vid).orElseThrow(()-> new RuntimeException("Vehicle not found"));

        //Checking if item is already in cart
        Optional<CartItem> existCartItem = cartItemRepository.findByShoppingCartAndVehicle(cart, vehicle);

        if (existCartItem.isPresent()) {
            CartItem cartItem = existCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        }else {
            CartItem cartItem = new CartItem(cart, vehicle, quantity);
            cartItemRepository.save(cartItem);
        }
        return cart;
    }

    public ResponseEntity<String> removeFromCart(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> updateCartItem(UpdateCartRequest request){
        CartItem cartItem = cartItemRepository.findById(request.getCartItemId())
                .orElseThrow(()-> new RuntimeException("Cart Item not found"));

        cartItem.setQuantity(request.getQuantity());
        cartItemRepository.save(cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
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

    public void clearCartByUserId(int userId) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByLoginUser_Id(userId);

        optionalCart.ifPresent(cart -> {
            List<CartItem> items = cartItemRepository.findByShoppingCart_CartId(cart.getCartId());
            cartItemRepository.deleteAll(items);
        });
    }



}
