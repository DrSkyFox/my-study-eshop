package ru.geekbrains.persist.model.accounts;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    @Column(name = "firstname")
    private String firstName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "fullname")
    private String fullname;

    @OneToMany(
            mappedBy = "address_id",
            cascade = CascadeType.ALL)
    private List<AddressUser> addressUserList;

    public UserInfo(User user, String firstName, String secondName, String fullname, List<AddressUser> addressUserList) {
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
        this.fullname = fullname;
        this.addressUserList = addressUserList;
    }

    public UserInfo() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<AddressUser> getAddressUserList() {
        return addressUserList;
    }

    public void setAddressUserList(List<AddressUser> addressUserList) {
        this.addressUserList = addressUserList;
    }
}
