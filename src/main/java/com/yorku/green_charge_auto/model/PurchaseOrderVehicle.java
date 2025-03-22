package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;

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

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
