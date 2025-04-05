package com.yorku.green_charge_auto.dto;

import com.yorku.green_charge_auto.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

        private String username;
        private String password;
        private Role role;

}
