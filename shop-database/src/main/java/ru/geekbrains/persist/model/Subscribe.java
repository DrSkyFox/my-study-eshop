package ru.geekbrains.persist.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "onsubscribedate")
    private LocalDate onSubscribeDate;

    public Subscribe(User user, Boolean active, LocalDate onSubscribeDate) {
        this.user = user;
        this.active = active;
        this.onSubscribeDate = onSubscribeDate;
    }

    public Subscribe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getOnSubscribeDate() {
        return onSubscribeDate;
    }

    public void setOnSubscribeDate(LocalDate onSubscribeDate) {
        this.onSubscribeDate = onSubscribeDate;
    }
}
