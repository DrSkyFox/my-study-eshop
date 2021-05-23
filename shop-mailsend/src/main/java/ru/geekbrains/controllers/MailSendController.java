package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.model.MessageModel;
import ru.geekbrains.service.MailSendingService;

@Controller
@RequestMapping("/mail_service")
public class MailSendController {


    private static final Logger logger = LoggerFactory.getLogger(MailSendController.class);

    private MailSendingService mailSendingService;

    @Autowired
    public MailSendController(MailSendingService mailSendingService) {
        this.mailSendingService = mailSendingService;
    }

    @RequestMapping("/email")
    private ModelAndView sendEmail() {
        return new ModelAndView("mailform", "message", new MessageModel());
    }




}
