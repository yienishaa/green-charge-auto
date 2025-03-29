package com.yorku.green_charge_auto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yorku.green_charge_auto.model.PurchaseOrder;
import com.yorku.green_charge_auto.service.ChatbotService;
import com.yorku.green_charge_auto.service.PurchaseOrderService;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

	@Autowired
	private ChatbotService chatbotService;

	@GetMapping("/hours")
	public String getHours() {
		return chatbotService.getHours();
	}

	@GetMapping("/order/{id}")
	public String getOrderStatus(@PathVariable Optional<Integer> id) {
		return chatbotService.getOrder(id);
	}
	
	@GetMapping("/recommend")
	public String suggestCar(@RequestParam int people, @RequestParam int cargo ) {
		return chatbotService.recommendCar(people, cargo);
	}
	
	@GetMapping("/electric")
	public String whyElectric() {
		return chatbotService.whyElectric();
	}

	
}
