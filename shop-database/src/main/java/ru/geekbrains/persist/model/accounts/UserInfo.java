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
    private String fullName;

    @OneToMany(
            mappedBy = "address_id",
            cascade = CascadeType.ALL)
    private List<AddressUser> addressUserList;

    @Column(name = "isactive")
    private Boolean isActive;

    public UserInfo(User user, String firstName, String secondName, String fullName, List<AddressUser> addressUserList) {
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullNname(String fullname) {
        this.fullName = fullname;
    }

    public List<AddressUser> getAddressUserList() {
        return addressUserList;
    }

    public void setAddressUserList(List<AddressUser> addressUserList) {
        this.addressUserList = addressUserList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", addressUserList=" + addressUserList +
                ", isActive=" + isActive +
                '}';
    }
}
