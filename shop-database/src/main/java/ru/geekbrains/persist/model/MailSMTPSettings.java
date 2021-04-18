package ru.geekbrains.persist.model;

import liquibase.pro.packaged.I;

import javax.persistence.*;

@Entity
public class MailSMTPSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String SMTPServer;

    @Column(length = 5, nullable = false)
    private Integer port;

    @Column(length = 1, nullable = false)
    private Boolean useSSL;

    @Column(length = 64, nullable = false)
    private String loginSMTP;

    @Column(length = 512, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String mailAddressFrom;

    @Column
    private Boolean enabled;

    @Column
    private Boolean deleted;

    public MailSMTPSettings(String SMTPServer, Integer port, Boolean useSSL, String loginSMTP, String password, String mailAddressFrom, Boolean enabled) {
        this.SMTPServer = SMTPServer;
        this.port = port;
        this.useSSL = useSSL;
        this.loginSMTP = loginSMTP;
        this.password = password;
        this.mailAddressFrom = mailAddressFrom;
        this.enabled = false;
        this.deleted = false;
    }

    public MailSMTPSettings() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSMTPServer() {
        return SMTPServer;
    }

    public void setSMTPServer(String SMTPServer) {
        this.SMTPServer = SMTPServer;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getUseSSL() {
        return useSSL;
    }

    public void setUseSSL(Boolean useSSL) {
        this.useSSL = useSSL;
    }

    public String getLoginSMTP() {
        return loginSMTP;
    }

    public void setLoginSMTP(String loginSMTP) {
        this.loginSMTP = loginSMTP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAddressFrom() {
        return mailAddressFrom;
    }

    public void setMailAddressFrom(String mailAddressFrom) {
        this.mailAddressFrom = mailAddressFrom;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}