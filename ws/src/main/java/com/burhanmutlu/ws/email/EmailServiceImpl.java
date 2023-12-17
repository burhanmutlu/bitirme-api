package com.burhanmutlu.ws.email;

import com.burhanmutlu.ws.util.FileRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FileRead fileRead;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public void sendEmail(String toEmail, String subject, String body) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(new InternetAddress(fromMail));
        message.setRecipients(MimeMessage.RecipientType.TO, toEmail);
        message.setText(body);
        message.setSubject(subject);

        String htmlTemplate = fileRead.readFile("newAccountMailMessage.html");

        htmlTemplate = htmlTemplate.replace("${name}", body);

        message.setContent(htmlTemplate,"text/html; charset=utf-8");

        javaMailSender.send(message);
    }
}
