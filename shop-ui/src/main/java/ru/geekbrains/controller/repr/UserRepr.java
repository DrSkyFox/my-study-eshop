package ru.geekbrains.controller.repr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserRepr {
    private Long id;

    @NotEmpty
    private String login;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty
    private String password;

    @JsonIgnore
    @NotEmpty
    private String matchingPassword;

    @Email
    private String email;

    private Set<Role> roles;

    public UserRepr() {
    }


}
