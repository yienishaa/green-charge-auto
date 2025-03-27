package com.yorku.green_charge_auto.service;

import java.util.Calendar;
import java.util.TimeZone;

public class ChatbotService {

	public String getHours() {
		String response = "We are ";
		Calendar now = Calendar
				.getInstance(TimeZone.getTimeZone("America/New_York"));
		int day = now.get(Calendar.DAY_OF_WEEK);
		switch (day) {
			case (0) :
				response += "open on Sunday from 10:00AM - 5:00PM!";
			case (1) :
				response += "closed on Monday, but we open Tuesday at 8:00AM!";
			case (2) :
				response += "open on Tuesday from 8:00AM - 6:00PM!";
			case (3) :
				response += "closed on Wednesday, but we open Thursday at 9:00AM!";
			case (4) :
				response += "open on Thursday from 9:00AM - 7:00PM!";
			case (5) :
				response += "open on Friday from 9:00AM - 7:00PM!";
			case (6) :
				response += "open on Saturday from 9:00AM - 7:00PM!";
		}
		return response;
	}
	
	public String getOrder(int orderNo) {
		String response = "";
		return response;
	}

	public String carQna(int people, int mileage, int frequency, boolean used) {
		String response = "";
		return response;
	}
}