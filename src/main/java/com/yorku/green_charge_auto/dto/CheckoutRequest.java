package com.yorku.green_charge_auto.dto;

import lombok.Data;
import java.util.List;

@Data
public class CheckoutRequest {

    private String fname;
    private String lname;

    private AddressDTO address;
    private List<CartItemDTO> cart;

    @Data
    public static class AddressDTO {
        private String street;
        private String city;
        private String postalCode;
    }

    @Data
    public static class CartItemDTO {
        private Integer vid;
        private Integer quantity;
    }
}
