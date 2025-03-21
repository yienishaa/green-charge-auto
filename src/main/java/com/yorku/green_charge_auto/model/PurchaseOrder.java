package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrder {

    @Id //To mention this is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "addressId")
    private Address address;

}
