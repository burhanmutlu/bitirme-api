package com.burhanmutlu.ws.email;

public interface EmailService {

    void sendEmail(String toEmail, String subject, String body);

}
