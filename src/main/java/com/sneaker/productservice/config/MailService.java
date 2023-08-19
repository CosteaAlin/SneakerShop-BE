package com.sneaker.productservice.config;

import com.sneaker.productservice.order.rest.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MailService {

    @Value("${email.from}")
    private String from;
    private final JavaMailSender emailSender;
    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(String email, String textMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Sneaker Shop");
        message.setText(textMessage);

        sendEmail(message);
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' HH:mm:ss");
        return now.format(formatter);
    }

    private void sendEmail(SimpleMailMessage message) {
        try {
            emailSender.send(message);
        } catch (Exception e) {
            log.error("There was an error sending the email: ", e);
        }
    }
}
