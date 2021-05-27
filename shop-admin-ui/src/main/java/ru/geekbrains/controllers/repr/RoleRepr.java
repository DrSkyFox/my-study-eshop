package ru.geekbrains.controllers.repr;

import ru.geekbrains.persist.model.accounts.Role;
import ru.geekbrains.persist.model.accounts.User;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class RoleRepr {


    private Long id;

    @NotEmpty
    private String name;


    private List<User> users;

    public RoleRepr() {
    }

    public RoleRepr(String name) {
        this.name = name;
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
