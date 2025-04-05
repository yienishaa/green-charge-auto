package com.yorku.green_charge_auto.model;

import com.yorku.green_charge_auto.constants.Colors;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_colors", indexes = {
        @Index(name = "idx_color_enum", columnList = "colors")
})
public class VehicleColors {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "colors")
    private Colors colors;


    @ManyToMany(mappedBy = "availableColors")
    private Set<Vehicle> vehicles = new HashSet<>();
}
