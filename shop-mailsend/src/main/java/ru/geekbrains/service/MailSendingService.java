package ru.geekbrains.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.model.User;


public interface MailSendingService {

    void send(final SimpleMailMessage simpleMailMessage);
}
