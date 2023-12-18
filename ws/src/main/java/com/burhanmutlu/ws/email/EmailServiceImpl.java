package com.burhanmutlu.ws.email;

import com.burhanmutlu.ws.user.UserService;
import com.burhanmutlu.ws.util.FileRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FileRead fileRead;

    private UserService userService;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public void sendWelcomeEmail(String toEmail) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(new InternetAddress(fromMail));
        message.setRecipients(MimeMessage.RecipientType.TO, toEmail);
        message.setSubject("Welcome to app!");

        String name = userService.getUserByEmail(toEmail).getName();

        String htmlTemplate = fileRead.readFile("welcomeMail.html");

        htmlTemplate = htmlTemplate.replace("${name}", name);

        message.setContent(htmlTemplate,"text/html; charset=utf-8");

        javaMailSender.send(message);
    }

    @Override
    public void sendActivationEmail(String toEmail, String activationToken) throws MessagingException, IOException {
       MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(new InternetAddress(fromMail));
        message.setRecipients(MimeMessage.RecipientType.TO, toEmail);
        message.setSubject("Activate account");


        String name = userService.getUserByEmail(toEmail).getName();

        String htmlTemplate = fileRead.readFile("activationMail.html");

        String url = "google.com/" + "/activation/" + activationToken;

        htmlTemplate = htmlTemplate.replace("${name}", name).replace("${url}", url);

        message.setContent(htmlTemplate,"text/html; charset=utf-8");

        javaMailSender.send(message);


    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String passwordResetToken) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(new InternetAddress(fromMail));
        message.setRecipients(MimeMessage.RecipientType.TO, toEmail);
        //message.setText(body);
        message.setSubject("Reset your password");

        String name = userService.getUserByEmail(toEmail).getName();

        String htmlTemplate = fileRead.readFile("resetPasswordMail.html");

        String url = "google.com";

        htmlTemplate = htmlTemplate.replace("${name}", name).replace("${url}", url);

        message.setContent(htmlTemplate,"text/html; charset=utf-8");

        javaMailSender.send(message);
    }
}
