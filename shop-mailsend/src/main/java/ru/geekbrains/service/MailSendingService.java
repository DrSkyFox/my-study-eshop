package ru.geekbrains.service;



import ru.geekbrains.model.MessageModel;



public interface MailSendingService {

    void send(MessageModel messageModel);

    void sendTest();
}
