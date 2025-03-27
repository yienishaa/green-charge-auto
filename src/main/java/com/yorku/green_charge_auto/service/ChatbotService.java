package com.yorku.green_charge_auto.service;

import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yorku.green_charge_auto.controller.PurchaseOrderController;
import com.yorku.green_charge_auto.model.PurchaseOrder;

@Service
public class ChatbotService {

	public String getHours() {
		String response = "We are ";
		Calendar now = Calendar
				.getInstance(TimeZone.getTimeZone("America/Toronto"));
		int day = now.get(Calendar.DAY_OF_WEEK);
		switch (day) {
			case (1) :
				response += "open on Sunday from 10:00AM - 5:00PM!";
				break;
			case (2) :
				response += "closed on Monday, but we open Tuesday at 8:00AM!";
				break;
			case (3) :
				response += "open on Tuesday from 8:00AM - 6:00PM!";
				break;
			case (4) :
				response += "closed on Wednesday, but we open Thursday at 9:00AM!";
				break;
			case (5) :
				response += "open on Thursday from 9:00AM - 7:00PM!";
				break;
			case (6) :
				response += "open on Friday from 9:00AM - 7:00PM!";
				break;
			case (7) :
				response += "open on Saturday from 9:00AM - 7:00PM!";
				break;
		}
		return response;
	}

	public String getOrder(int orderNo) {
		String url = "http:localhost:8080/orders/" + orderNo;
		String response = "";
		// result = getForObject(url, );
		return response;
	}

	public String carQna(int people, int distance, int traffic, int cargo) {
	    String response = "You will want a ";
	    String justify = "";

	    // Decision for electric, hybrid, or gasoline based on traffic and distance
	    if (traffic == 1 || distance <= 250) {
	        response += "electric ";
	        justify += "Electric cars are ideal for short trips and stop-and-go traffic, as they offer smooth acceleration and are efficient in city driving. ";
	    } else {
	        response += "gasoline ";
	        justify += "Gasoline cars are better suited for longer distances and higher speeds, especially for longer trips or if you need a larger driving range. ";
	    }
	    
	    // Adding hybrid options for versatility
	    response += "or hybrid ";
	    justify += "Hybrid vehicles offer a good balance between fuel efficiency and range, making them great for both short city commutes and longer trips. ";
	    
	    // Consideration for cargo space
	    if (cargo == 0) {
	        if (people <= 2) {
	            response += "coupe.";
	            justify += "A coupe is a compact, stylish choice ideal for smaller groups, offering limited cargo space but better maneuverability. ";
	        } else {
	            response += "compact sedan or hatchback.";
	            justify += " Compact sedans and hatchbacks are practical for small to medium-sized groups, offering good fuel economy and enough space for daily needs. ";
	        }
	    } else if (cargo == 1) {
	        if (people <= 5) {
	            response += "compact or midsize SUV, or a truck, depending on how much you carry.";
	            justify += " Compact or midsize SUVs provide more space for passengers and cargo, while trucks can be useful if you need to transport larger loads. ";
	        }
	    } else {
	        response += "large SUV, or a minivan.";
	        justify += " A large SUV or minivan is ideal for families or groups who need a lot of space for passengers and cargo, providing ample comfort and versatility. ";
	    }

	    return response + justify;
	}

}