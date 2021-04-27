package ru.geekbrains.registration;

import java.util.Locale;


import org.springframework.context.ApplicationEvent;
import ru.geekbrains.persist.model.User;


public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final User user;
    private final Locale locale;


    public OnRegistrationCompleteEvent(Object source, String appUrl, User user, Locale locale) {
        super(source);
        this.appUrl = appUrl;
        this.user = user;
        this.locale = locale;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public User getUser() {
        return user;
    }

    public Locale getLocale() {
        return locale;
    }
}
