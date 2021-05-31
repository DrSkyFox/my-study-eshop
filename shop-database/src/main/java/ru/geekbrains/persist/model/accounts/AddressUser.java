package ru.geekbrains.persist.model.accounts;

import ru.geekbrains.persist.model.handbooks.Country;

import javax.persistence.*;

@Entity
@Table(name = "addressuser")
public class AddressUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserInfo userInfo;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "country_id")
    private Country country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "isprimary")
    private Boolean isPrimary;

    @Column(name = "isactive")
    private Boolean isActive;


    public AddressUser(UserInfo userInfo, Country country, String city, String address) {
        this.userInfo = userInfo;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public AddressUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressUser{" +
                "id=" + id +
                ", userInfo=" + userInfo +
                ", country=" + country +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", isPrimary=" + isPrimary +
                ", isActive=" + isActive +
                '}';
    }
}
