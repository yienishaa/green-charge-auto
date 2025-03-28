package com.yorku.green_charge_auto.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

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

	public String getOrder(Optional<Integer> orderNo) {
		if(orderNo.isPresent()) {
			int order = orderNo.get();
		String url = "http:localhost:8080/orders/" + orderNo;
		Date requestDate = Calendar.getInstance().getTime();
		String response = "As of " + requestDate+ " Order " + order + "'s status is: ";
		// result = getForObject(url, );
		response += "";
		return response;
		}
		else {
			return "You did not enter a valid order number";
		}
	}

	public String recommendCar(int people, int cargo) {
		String response = "You will want a ";
		String justify = "";

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

	public String whyElectric() {
		return "Electric cars offer several key benefits:\r\n"
				+ "\r\n"
				+ "1. Environmentally Friendly: They produce zero tailpipe emissions, reducing air pollution and contributing to a cleaner planet.\r\n"
				+ "\r\n"
				+ "2. Lower Costs: EVs are cheaper to fuel and maintain, with fewer moving parts and no oil changes.\r\n"
				+ "\r\n"
				+ "3. Quiet & Smooth Ride: EVs operate quietly and provide smooth, instant acceleration.\r\n"
				+ "\r\n"
				+ "4. Incentives: Tax credits and rebates often make them more affordable upfront.\r\n"
				+ "\r\n"
				+ "5. Energy Efficiency: EVs use energy more efficiently than gas cars, reducing waste.\r\n"
				+ "\r\n"
				+ "6. Convenient Charging: You can charge at home, eliminating frequent trips to the gas station.\r\n"
				+ "\r\n"
				+ "7. Tech-Forward: EVs often come with advanced features like autonomous driving and over-the-air updates.";
	}

}