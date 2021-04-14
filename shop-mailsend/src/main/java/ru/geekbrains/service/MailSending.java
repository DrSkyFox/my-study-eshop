package ru.geekbrains.service;


import org.springframework.stereotype.Service;

@Service
public interface MailSending {
    void sendMailRegistrationForm();
    void sendMailForgotPassword();
    void sendMailSubscribe();
    void unsubscribe();
    void subscribe();
}
