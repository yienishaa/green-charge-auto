package com.yorku.green_charge_auto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PurchaseOrderVehicle")
public class PurchaseOrderVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poId")
    private Integer poId;

    @ManyToOne
    @JoinColumn(name = "vid")
    private Vehicle vehicle;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "orderId")
    private PurchaseOrder purchaseOrder;

}
