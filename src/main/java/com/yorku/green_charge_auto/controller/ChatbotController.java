package com.yorku.green_charge_auto.controller;

import java.util.Optional;

import com.yorku.green_charge_auto.model.ChatbotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yorku.green_charge_auto.service.ChatbotService;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatbotController {

	@Autowired
	private ChatbotService chatbotService;

	@GetMapping
	public ChatbotResponse getChatbotInfo() {
		return chatbotService.welcome();
	}

	@GetMapping("/hours")
	public ChatbotResponse getHours() {
		return chatbotService.getHours();
	}

	@GetMapping("/loan")
	public ChatbotResponse getLoan() {
		return chatbotService.getLoan();
	}

	@GetMapping("/order")
	public ChatbotResponse getOrderNumber() {
		return chatbotService.getOrder();
	}

	@GetMapping("/order/{id}")
	public ChatbotResponse getOrderStatus(@PathVariable Optional<Integer> id) {
		return chatbotService.getOrderStatus(id);
	}

	@GetMapping("/questionnaire/{id}")
	public ChatbotResponse getQuestionnaire(@PathVariable int id, @RequestParam int people) {
		return chatbotService.askQuestions(id, people);
	}

	@GetMapping("/recommend")
	public ChatbotResponse suggestCar(@RequestParam int people, @RequestParam int cargo) {
		return chatbotService.recommendCar(people, cargo);
	}

	@GetMapping("/electric")
	public ChatbotResponse whyElectric() {
		return chatbotService.whyElectric();
	}
}