package util;

import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {

    public static void sendOTP(String email, String otp)
            throws Exception {
    	
    


        String sender = "iland12maimom@gmail.com";
        String appPassword =	"zsdt kefu jszf xsug";

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
            new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(sender, appPassword);
                }
            });

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sender));

        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(email)
        );

        message.setSubject("Signup OTP Verification");

        message.setText(
            "Your OTP is: " + otp +
            "\n\nOTP expires in 5 minutes."
        );

        Transport.send(message);
    }
}