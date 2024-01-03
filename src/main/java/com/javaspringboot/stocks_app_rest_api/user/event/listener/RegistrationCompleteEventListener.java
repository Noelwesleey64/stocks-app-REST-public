package com.javaspringboot.stocks_app_rest_api.user.event.listener;

import com.javaspringboot.stocks_app_rest_api.user.entity.UserTbl;
import com.javaspringboot.stocks_app_rest_api.user.event.RegistrationCompleteEvent;
import com.javaspringboot.stocks_app_rest_api.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

//Class to listen to custom event type RegistrationCompleteEvent
//The ApplicationListener interface is a generic interface that allows classes to declare the event type that they are interested in.
// When registered with a Spring application context, events will be filtered accordingly,
// and the listener will be invoked for matching event objects only
// @Slf4j automatically creates a Logger instance for the class that is annotated with it, using the SLF4J API.
@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    //Dependency Inject a user service object which contains important methods we will use
    @Autowired
    private UserService userService;

    //Dependency Inject a javaMailSender object which we will use to send our email
    @Autowired
    private JavaMailSender javaMailSender;


    //Method to implement what happens when the registration complete event occurs
    //it takes Registration complete object which is named event
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get newly registered User
        UserTbl user = event.getUser();


        //2.  Create a verification token for user
        //UUID.randomUUID().toString() method,
        // which generates a random and unique identifier and returns its string representation.
        String verificationToken = UUID.randomUUID().toString();


        //3. save the verification token for the user
        userService.saveUserVerificationToken(user, verificationToken);


        //4. Build the verification url to be sent to the user
        String url = event.getApplicationUrl()+"/register/verifyEmail?token=" + verificationToken;


        //5. send the email
        try {
            //call the method to send email
            sendVerificationEmail(url, user);
            //catch and throw exception incase there's any
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration: {}", url);



    }

    //Method to send verification email
    //throws an exception in case there is an exception while sending email
    public void sendVerificationEmail(String url, UserTbl user) throws MessagingException, UnsupportedEncodingException {

        //Store subject of email
        String subject = "Email Verification";

        //Store name of the sender
        String senderName = "StocksApp";

        //Store the content of the email, which is a html code with the link and every other detail
        String content = "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        p {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            font-size: 16px;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "\n" +
                "a:link {\n" +
                "  color: blue; /* unvisited link */\n" +
                "}\n"+
                "        button {\n" +
                "            background-color: #4CAF50;\n" +
                "            border: none;\n" +
                "            color: white;\n" +
                "            padding: 15px 32px;\n" +
                "            text-align: center;\n" +
                "            text-decoration: none;\n" +
                "            display: inline-block;\n" +
                "            font-size: 16px;\n" +
                "            margin: 4px 2px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p> Hi "+ user.getFirstName()+", Thank you for registering with us. To complete your account verification, please click the link below.</p>\n" +
                "<a href='" + url + "'>Click this link to complete verification</a>" +
                "</body>\n" +
                "</html>\n";

        //Codes to send the message
        MimeMessage message = javaMailSender.createMimeMessage();

        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("springsmtp254@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        javaMailSender.send(message);



    }
}
