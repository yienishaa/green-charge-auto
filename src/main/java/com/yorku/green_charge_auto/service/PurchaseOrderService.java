package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.PurchaseOrder;
import com.yorku.green_charge_auto.model.PurchaseOrderVehicle;
import com.yorku.green_charge_auto.model.OrderStatus;
import com.yorku.green_charge_auto.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getOrderById(int orderId) {
        return purchaseOrderRepository.findById(orderId);
    }

    public PurchaseOrder createOrder(PurchaseOrder order) {
        double totalPrice = order.getPurchaseOrderVehicles().stream()
                .mapToDouble(pov -> pov.getVehicle().getPrice())
                .sum();
        order.setTotalPrice(totalPrice);
        return purchaseOrderRepository.save(order);
    }

    public PurchaseOrder updateOrderStatus(int orderId, OrderStatus status) {
        return purchaseOrderRepository.findById(orderId)
                .map(order -> {
                    order.setStatus(status);
                    return purchaseOrderRepository.save(order);
                }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(int orderId) {
        purchaseOrderRepository.deleteById(orderId);
    }
}
