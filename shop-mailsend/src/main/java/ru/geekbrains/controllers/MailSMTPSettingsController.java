package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.geekbrains.persist.model.MailSMTPSettings;
import ru.geekbrains.service.MailSendingService;
import ru.geekbrains.serviceimplementation.MailSendingImplementation;

@Controller
public class MailSMTPSettingsController {

    private static final Logger logger = LoggerFactory.getLogger(MailSMTPSettingsController.class);

    @Autowired
    private MailSendingService sendingService;

    public MailSMTPSettingsController(MailSendingService sendingService) {
        this.sendingService = sendingService;
    }




}
