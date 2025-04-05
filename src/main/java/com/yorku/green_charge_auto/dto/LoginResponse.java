package com.yorku.green_charge_auto.dto;

import com.yorku.green_charge_auto.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private int id;
    private Role role;
}
