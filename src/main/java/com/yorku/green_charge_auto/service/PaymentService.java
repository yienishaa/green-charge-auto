package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.Payment;
import com.yorku.green_charge_auto.repository.PaymentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private final AtomicInteger counter = new AtomicInteger(0);

    public Payment processPayment(Integer orderId, Double amount) {
        int current = counter.incrementAndGet();
        String status = (current % 3 == 0) ? "DENIED" : "APPROVED";

        Payment payment = new Payment(orderId, amount, status);
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
