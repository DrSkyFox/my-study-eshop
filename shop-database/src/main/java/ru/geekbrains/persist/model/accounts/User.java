package ru.geekbrains.persist.model.accounts;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(columnList = "login", name = "l_index", unique = true),
        @Index(columnList = "fullname", name = "fn_index")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login" ,length = 32, nullable = false)
    private String login;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "calendar")
    private Calendar calendar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_info")
    private UserInfo userInfo;

    @Column(name = "isactive")
    private Boolean isActive;

    public User() {
    }

    public User(String login, String password, String email, Boolean enabled, Calendar calendar, Set<Role> role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.calendar = calendar;
        this.roles = roles;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String login, String password, String email, Boolean enabled, Calendar calendar, Set<Role> roles, UserInfo userInfo) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.calendar = calendar;
        this.roles = roles;
        this.userInfo = userInfo;
        this.isActive = true;
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

    public void setLogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }


}
