package com.yorku.green_charge_auto.model;

import com.yorku.green_charge_auto.constants.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrder {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;

    @Setter
    @Column(name = "fname")
    private String fname;

    @Setter
    @Column(name = "lname")
    private String lname;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Setter
    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Setter
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @Getter
    @Setter
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderVehicle> purchaseOrderVehicles;

    public PurchaseOrder() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
