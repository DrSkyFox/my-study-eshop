package ru.geekbrains.persist.model.handbooks;

import javax.persistence.*;

@Entity
@Table(name = "countries",
indexes = {
        @Index(columnList = "phoneCode", name = "pc_index"),
        @Index(columnList = " country_name", name = "cn_index", unique = true)
    })
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code")
    private String code;

    @Column(name = "country_name")
    private String countryName;

    @Column(name ="phoneCode")
    private String phoneCode;

    public Country(String code, String countryName, String phoneCode) {
        this.code = code;
        this.countryName = countryName;
        this.phoneCode = phoneCode;
    }

    public Country() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
