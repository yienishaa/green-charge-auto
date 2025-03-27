package com.yorku.green_charge_auto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yorku.green_charge_auto.service.ChatbotService;

public class ChatbotController {
	
	@Autowired
	private ChatbotService chatbotService;
	
	@GetMapping("/hours")
	public String getHours() {
		return chatbotService.getHours();
	}

}
