package ru.geekbrains.controller.repr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;


import javax.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.Set;

//DTO
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


    private Set<Role> roles;


    public UserRepr(String username) {
        this.login = username;
    }

    public UserRepr(User user) {
        this.id = user.getId();
        this.login = user.getLogin()
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public UserRepr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRepr userRepr = (UserRepr) o;
        return id.equals(userRepr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
