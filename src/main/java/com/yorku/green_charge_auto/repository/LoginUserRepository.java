package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer> {
    Optional<LoginUser> findByUsername(String username);
}
