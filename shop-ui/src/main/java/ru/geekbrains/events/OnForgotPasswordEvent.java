package ru.geekbrains.events;


import org.springframework.context.ApplicationEvent;
import ru.geekbrains.persist.model.User;

public class OnForgotPasswordEvent extends ApplicationEvent implements EmailEvents {


    private final User user;
    private final String appUrl;

    public OnForgotPasswordEvent(final User user, final String appUrl) {
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
        return "OnForgotPasswordEvent{" +
                "user=" + user +
                ", appUrl='" + appUrl + '\'' +
                '}';
    }
}


