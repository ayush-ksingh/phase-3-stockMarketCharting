package com.user.demo.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public static void sendEmail(String email,String name)
	{
		String link = "https://localhost:11115/confirm_user/" + name;
		final String username = "username@gmail.com";//the gmail address should have less secure apps enable to run the mail service
        final String password = "password";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("username@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("StockCharting-Registration Confirmation Mail");
            message.setText("Dear" + name +
                    "\n\n Please click on the below link to confirm your registration-\n\n" + link + "\n\n Thank You");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
