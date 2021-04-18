package ru.geekbrains.service;


import org.springframework.stereotype.Service;
import ru.geekbrains.persist.model.User;


public interface MailSending {
    void sendMailRegistrationForm(User user);
    void sendMailForgotPassword();
    void sendMailSubscribe();
    void unsubscribe();
    void subscribe();



}
