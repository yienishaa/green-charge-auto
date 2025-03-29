package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.PurchaseOrder;
import com.yorku.green_charge_auto.constants.OrderStatus;
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

    public PurchaseOrder checkout(CheckoutRequest request) {
    PurchaseOrder order = new PurchaseOrder();
    order.setFname(request.getFname());
    order.setLname(request.getLname());
    order.setStatus(OrderStatus.PLACED);

    Address addr = new Address();
    addr.setStreet(request.getAddress().getStreet());
    addr.setCity(request.getAddress().getCity());
    addr.setPostalCode(request.getAddress().getPostalCode());
    order.setAddress(addr);

    List<PurchaseOrderVehicle> povList = new ArrayList<>();
    for (CheckoutRequest.CartItemDTO item : request.getCart()) {
        Vehicle vehicle = vehicleRepository.findById(item.getVid())
                .orElseThrow(() -> new RuntimeException("Vehicle not found: " + item.getVid()));

        PurchaseOrderVehicle pov = new PurchaseOrderVehicle();
        pov.setVehicle(vehicle);
        pov.setPurchaseOrder(order);
        povList.add(pov);
    }

    order.setPurchaseOrderVehicles(povList);

    double total = povList.stream().mapToDouble(p -> p.getVehicle().getPrice()).sum();
    order.setTotalPrice(total);

    return purchaseOrderRepository.save(order);
}
}
