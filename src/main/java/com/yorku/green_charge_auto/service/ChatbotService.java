package com.yorku.green_charge_auto.service;

import java.util.*;

import com.yorku.green_charge_auto.model.ChatbotResponse;
import com.yorku.green_charge_auto.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatbotService {
    private final PurchaseOrderService orderService;

    @Autowired
    public ChatbotService(PurchaseOrderService orderService) {
        this.orderService = orderService;
    }

    public ChatbotResponse welcome() {
        Map<String, String> options = new HashMap<>();
        String response = "Hello! 👋 How can I assist you today?";
        options.put("🚗 Help me pick a car.", "chatbot/questionnaire/1?people=1");
        options.put("🔌 Why should I buy an electric car?", "chatbot/electric");
        options.put("💵 I need a loan.", "chatbot/loan");
        options.put("🏪 What are your store hours?", "chatbot/hours");
        options.put("📦 Help me track my order.","chatbot/order");
 
        return new ChatbotResponse(response, options);
    }

    public ChatbotResponse getHours() {
        Map<String, String> options = new HashMap<>();
        options.put("🤔 I have another question", "chatbot");
        String response = "We are ";
        Calendar now = Calendar
                .getInstance(TimeZone.getTimeZone("America/Toronto"));
        int day = now.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case (1):
                response += "open on Sunday from 10:00AM - 5:00PM!";
                break;
            case (2):
                response += "closed on Monday, but we open Tuesday at 8:00AM!";
                break;
            case (3):
                response += "open on Tuesday from 8:00AM - 6:00PM!";
                break;
            case (4):
                response += "closed on Wednesday, but we open Thursday at 9:00AM!";
                break;
            case (5):
                response += "open on Thursday from 9:00AM - 7:00PM!";
                break;
            case (6):
                response += "open on Friday from 9:00AM - 7:00PM!";
                break;
            case (7):
                response += "open on Saturday from 9:00AM - 7:00PM!";
                break;
        }

        return new ChatbotResponse(response, options);
    }

public  ChatbotResponse getOrder() {
        Map<String, String> options = new HashMap<>();
        options.put("", "");
        String response = "Sure I can help you track your order! What's your order number?";
        return new ChatbotResponse(response, options);
}

    public ChatbotResponse getOrderStatus(Optional<Integer> orderNo) {
        String response;
        Map<String, String> options = new HashMap<>();
        if (orderNo.isPresent()) {

            int order = orderNo.get();

            Date requestDate = Calendar.getInstance().getTime();
            response = "As of " + requestDate + " the status of order #" + order + " is: ";
            Optional<PurchaseOrder> status = orderService.getOrderById(order);
            if (status.isPresent()) {
                response += status.get().getStatus();
            } else {
                response += "Not available.";
            }
            options.put("📦 I want to track a different order.", "chatbot/order");
            options.put("🤔 I have another question", "chatbot");
            return new ChatbotResponse(response, options);


        } else {
            response = "You did not enter a valid order number";

            options.put("📦 Try again.", "chatbot/order");
            options.put("🤔 I have another question", "chatbot");

        }
        return new ChatbotResponse(response, options);
    }

    public ChatbotResponse getLoan() {
        Map<String, String> options = new HashMap<>();
        options.put("🤑 Yes, take me there!", "");
        options.put("🤔 No, I have a different question.", "chatbot");
        String response = "We have a loan calculator to help you calculate loan rates! Shall I take you there?";
        return new ChatbotResponse(response, options);
    }

public ChatbotResponse askQuestions(int id, int people) {
        String response = "";
    Map<String, String> options = new HashMap<>();
        switch(id) {
            case 1:
                response += "How many people do you tend to drive on average?";
                options.put("🚻 1-2", "chatbot/questionnaire/2?people=2");
                options.put("🚻 3-5", "chatbot/questionnaire/2?people=5");
                options.put("🚻 6+", "chatbot/questionnaire/2?people=6");

                break;
            case 2:
                response += "How much cargo do you typically carry?";
                options.put("👛 Little to None (Groceries)", "chatbot/recommend?people="+people+"&cargo=0");
                options.put("🛋️ A lot (Sofa)", "chatbot/recommend?people="+people+"&cargo=1");
        }
        return new ChatbotResponse(response, options);


}

    public ChatbotResponse recommendCar(int people, int cargo) {
        String response = "You will want a ";
        String justify = "";
        Map<String, String> options = new HashMap<>();
        options.put("🤔 I have another question", "chatbot");
        options.put("🚗 Take me to the cars!", "//");
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
            } else {
                response += "large SUV, or a minivan.";
                justify += " A large SUV or minivan is ideal for families or groups who need a lot of space for passengers and cargo, providing ample comfort and versatility. ";
            }
        }
            else {
                response += "... wait how did you even get here?";
                justify += " Leaving this in because it's funny but congrats! You somehow bypassed the parameters and got here, so have a cookie! 🍪";
            }


        return new ChatbotResponse(response + " " + justify, options);
    }

    public ChatbotResponse whyElectric() {
        Map<String, String> options = new HashMap<>();
        options.put("🤔 I have another question", "chatbot");
        options.put("🚗 Help me pick a car.", "chatbot/recommend");
        String response = """
                Electric cars offer several key benefits:\r
                \r
                1. Environmentally Friendly: They produce zero tailpipe emissions, reducing air pollution and contributing to a cleaner planet.\r
   
                2. Lower Costs: EVs are cheaper to fuel and maintain, with fewer moving parts and no oil changes.\r

                3. Quiet & Smooth Ride: EVs operate quietly and provide smooth, instant acceleration.\r
    
                4. Energy Efficiency: EVs use energy more efficiently than gas cars, reducing waste.\r

                5. Tech-Forward: EVs often come with advanced features like autonomous driving and over-the-air updates.""";

        return new ChatbotResponse(response, options);
    }

}