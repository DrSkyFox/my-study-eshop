package ru.geekbrains.events;

import org.springframework.context.ApplicationEvent;
import ru.geekbrains.persist.model.User;


public class OnRegistrationCompleteEvent extends ApplicationEvent implements EmailEvents {

    private final String appUrl;
    private final User user;


    public OnRegistrationCompleteEvent(final User user, final String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getURL() {
        return appUrl;
    }

    @Override
    public String toString() {
        return "OnRegistrationCompleteEvent{" +
                "appUrl='" + appUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
