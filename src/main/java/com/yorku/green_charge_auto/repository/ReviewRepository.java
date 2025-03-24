package com.yorku.green_charge_auto.repository;

import com.yorku.green_charge_auto.dto.ReviewResponse;
import com.yorku.green_charge_auto.model.Reviews;
import com.yorku.green_charge_auto.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Integer> {

    List<Reviews> findByVehicleVid(Integer vid);
}
