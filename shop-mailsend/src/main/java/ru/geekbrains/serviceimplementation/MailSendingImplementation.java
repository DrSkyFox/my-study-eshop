package ru.geekbrains.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.service.MailSending;

public class MailSendingImplementation implements MailSending {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendMailRegistrationForm(User user) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Registration Confirmation");
        msg.setText("Please click for complete registration: " + );
    }

    @Override
    public void sendMailForgotPassword() {

    }

    @Override
    public void sendMailSubscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void subscribe() {

    }
}
