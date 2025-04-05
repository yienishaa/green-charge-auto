package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
