package ru.geekbrains.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.repo.MailSMTPSettingsRepository;
import ru.geekbrains.service.MailSendingService;



@Service
public class MailSendingImplementation implements MailSendingService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailSMTPSettingsRepository smtpSettingsRepository;

    public MailSendingImplementation(MailSMTPSettingsRepository smtpSettingsRepository) {
        this.smtpSettingsRepository = smtpSettingsRepository;
    }

    @Override
    public void send(SimpleMailMessage simpleMailMessage) {
        javaMailSender.send(simpleMailMessage);
    }

}
