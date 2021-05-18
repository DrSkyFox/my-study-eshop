package ru.geekbrains.utils;

import ru.geekbrains.persist.model.User;
import ru.geekbrains.replication.UserRepr;

public class ReplicationUtils {

    public static User convertReprToEntity(UserRepr userRepr) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setLogin(userRepr.getLogin());
        user.setPassword(userRepr.getPassword());
        user.setEmail(userRepr.getEmail());
        user.setCalendar(userRepr.getCalendar());
        user.setEnabled(user.getEnabled());
        user.setRoles(user.getRoles());
        return user;
    }

}
