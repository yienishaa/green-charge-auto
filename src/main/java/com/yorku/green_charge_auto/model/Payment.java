package com.yorku.green_charge_auto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status; // APPROVED or DENIED

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public Payment() {
        this.timestamp = LocalDateTime.now();
    }

    public Payment(Integer orderId, Double amount, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
