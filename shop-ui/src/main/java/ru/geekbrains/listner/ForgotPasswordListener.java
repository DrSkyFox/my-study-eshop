package ru.geekbrains.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.OnForgotPasswordEvent;
import ru.geekbrains.model.MessageModel;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.service.IUserService;
import ru.geekbrains.service.MailSendingService;

import java.util.UUID;

@Component
public class ForgotPasswordListener implements ApplicationListener<OnForgotPasswordEvent> {

    private final static Logger logger = LoggerFactory.getLogger(ForgotPasswordListener.class);

    private final IUserService service;

    private final MailSendingService mailSendingService;


    @Autowired
    public ForgotPasswordListener(IUserService service, MailSendingService mailSendingService) {
        this.service = service;
        this.mailSendingService = mailSendingService;
    }

    @Override
    public void onApplicationEvent(final OnForgotPasswordEvent event) {
        this.forgotPassword(event);
    }

    private void forgotPassword(final OnForgotPasswordEvent event) {
        logger.info("User registration complete. Send confirm message to email: {}", event.getUser().getEmail());
        final User user  = event.getUser();

        String token =  service.createPasswordResetTokenForUser(user);
        logger.info("User forgot password request complete. Send recovery password link message to email: email info: {},  token : {}", user.getEmail(), token);
        if(token == null) {
            logger.warn("Token is null");
            return;
        }
        final String subject = "Reset Password";
        final String resetUrl = event.getURL() + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        logger.info("reset url: {}", resetUrl);

        logger.info("mail send...................................");
        try {
            mailSendingService.send(new MessageModel(
                    event.getUser().getEmail(),
                    subject,
                    "Please open the following URL to reset your password: \r\n" + resetUrl));

        } catch (Exception e) {
            // TODO: 16.05.2021 Add logic with error
        }
        logger.info("mail sent..................................");
    }
}
