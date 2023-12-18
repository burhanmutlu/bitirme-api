package com.burhanmutlu.ws.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailService {

    void sendWelcomeEmail(String toEmail) throws MessagingException, IOException;

    void sendActivationEmail(String toEmail, String activationToken) throws MessagingException, IOException;

    void sendPasswordResetEmail(String toEmail, String passwordResetToken) throws MessagingException, IOException;

}
