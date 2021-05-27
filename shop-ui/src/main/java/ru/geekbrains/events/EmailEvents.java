package ru.geekbrains.events;

import ru.geekbrains.persist.model.accounts.User;

public interface EmailEvents {
    User getUser();
    String getURL();
}
