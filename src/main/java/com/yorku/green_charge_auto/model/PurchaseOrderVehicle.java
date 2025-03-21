package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseOrderVehicle")
public class PurchaseOrderVehicle {

    @Id //To mention this is the primary key
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
