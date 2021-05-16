package ru.geekbrains.events;

import ru.geekbrains.persist.model.User;

public interface EmailEvents {
    User getUser();
    String getURL();
}
