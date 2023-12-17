package com.burhanmutlu.ws.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailService {

    void sendEmail(String toEmail, String subject, String body) throws MessagingException, IOException;

}
