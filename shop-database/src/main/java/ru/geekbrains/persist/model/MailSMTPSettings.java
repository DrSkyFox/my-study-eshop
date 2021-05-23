package ru.geekbrains.persist.model;

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

    @Column(length = 64, nullable = false)
    private String login;

    @Column(length = 512, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String mailAddressFrom;

    @Column(length = 1, nullable = false)
    private Boolean mailServerStartTls;

    @Column(length = 1, nullable = false)
    private Boolean mailServerAuth;

    public MailSMTPSettings(String SMTPServer, Integer port, String login, String password, String mailAddressFrom, Boolean mailServerStartTls, Boolean mailServerAuth) {
        this.SMTPServer = SMTPServer;
        this.port = port;
        this.login = login;
        this.password = password;
        this.mailAddressFrom = mailAddressFrom;
        this.mailServerStartTls = mailServerStartTls;
        this.mailServerAuth = mailServerAuth;
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

    public String getMailAddressFrom() {
        return mailAddressFrom;
    }

    public void setMailAddressFrom(String mailAddressFrom) {
        this.mailAddressFrom = mailAddressFrom;
    }

    public Boolean getMailServerStartTls() {
        return mailServerStartTls;
    }

    public void setMailServerStartTls(Boolean mailServerStartTls) {
        this.mailServerStartTls = mailServerStartTls;
    }

    public Boolean getMailServerAuth() {
        return mailServerAuth;
    }

    public void setMailServerAuth(Boolean mailServerAuth) {
        this.mailServerAuth = mailServerAuth;
    }
}
