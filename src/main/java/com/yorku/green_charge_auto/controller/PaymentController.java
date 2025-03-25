package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.Payment;
import com.yorku.green_charge_auto.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Map<String, Object> payload) {
        Integer orderId = (Integer) payload.get("orderId");
        Double amount = Double.valueOf(payload.get("amount").toString());
        Payment payment = paymentService.processPayment(orderId, amount);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }
}
