package ru.geekbrains.listner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.OnRegistrationCompleteEvent;
import ru.geekbrains.model.MessageModel;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.service.MailSendingService;
import ru.geekbrains.service.IUserService;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    private final IUserService service;

    private final MailSendingService mailSendingService;


    @Autowired
    public RegistrationListener(IUserService service,  MailSendingService mailSendingService) {
        this.service = service;
        this.mailSendingService = mailSendingService;
    }

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        logger.info("Registration event start: event {}", event.toString());
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        logger.info("User registration complete. Send confirm message to email: {}", event.toString());
        final User user = event.getUser();

        String token =  service.createVerificationTokenForUser(user);

        logger.info("User registration complete. Send confirm message to email: email info: {},  token : {}", user.getEmail(), token);
        if(token == null) {
            logger.warn("Token is null");
            return;
        }

        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getURL() + "/registrationConfirm?token=" + token;


        logger.info("mail send...................................");
        try {
            mailSendingService.send(new MessageModel(
                    event.getUser().getEmail(),
                    subject,
                    "Please open the following URL to verify your account: \r\n" + confirmationUrl));
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        logger.info("mail sent..................................");
    }

    //



}
