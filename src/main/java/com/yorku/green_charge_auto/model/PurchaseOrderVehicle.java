package com.yorku.green_charge_auto.model;

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
    @JoinColumn(name = "orderId")
    private PurchaseOrder purchaseOrder;

}
