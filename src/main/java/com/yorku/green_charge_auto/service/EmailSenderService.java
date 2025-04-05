package com.yorku.green_charge_auto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String tempPassword) {

        String subject = "Green Charge Auto Registration";

        String body = "Hello John,\n\n"
                + "Thank you for registering with us.\n"
                + "We're excited to have you on board.\n\n"
                + "Your password is " + tempPassword + "\n"
                + "Best regards,\n"
                + "The Team";

        writeEmail(toEmail, subject, body);

    }

    public void sendResetPasswordEmail(String toEmail, String tempPassword) {

        String subject = "Green Charge Auto Registration";

        String body = "Hello John,\n\n"
                + "Here is your new password " + tempPassword + "\n"
                + "Best regards,\n"
                + "The Team";

        writeEmail(toEmail, subject, body);

    }

    private void writeEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yosemitepod1test@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

    }
}
