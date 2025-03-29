package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.PurchaseOrder;
import com.yorku.green_charge_auto.constants.OrderStatus;
import com.yorku.green_charge_auto.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable int id) {
        Optional<PurchaseOrder> order = purchaseOrderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PurchaseOrder createOrder(@RequestBody PurchaseOrder order) {
        return purchaseOrderService.createOrder(order);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PurchaseOrder> updateOrderStatus(@PathVariable int id, @RequestParam OrderStatus status) {
        try {
            return ResponseEntity.ok(purchaseOrderService.updateOrderStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        purchaseOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<PurchaseOrder> checkout(@RequestBody CheckoutRequest request) {
        try {
            PurchaseOrder order = purchaseOrderService.checkout(request);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
