package ru.geekbrains.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.MessageModel;
import ru.geekbrains.service.MailSendingService;



@Service
public class MailSendingImplementation implements MailSendingService {


    private JavaMailSender mailSender;

    private Environment env;

    @Autowired
    public MailSendingImplementation(JavaMailSender mailSender, Environment env) {
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    public void send(MessageModel messageModel)
    {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(env.getProperty("spring.mail.fromAddress"));
        email.setTo(messageModel.getRecipientAddress());
        email.setText(messageModel.getText());
        email.setSubject(messageModel.getSubject());
    }

    @Override
    public void sendTest() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(env.getProperty("spring.mail.fromAddress"));
        message.setFrom(env.getProperty("spring.mail.fromAddress"));
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        mailSender.send(message);

    }
}
