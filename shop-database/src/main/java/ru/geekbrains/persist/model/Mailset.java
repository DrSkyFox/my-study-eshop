package ru.geekbrains.persist.model;

import javax.persistence.*;

@Entity
@Table(name = "mailset")
public class Mailset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "smtpserver", length = 64, nullable = false)
    private String SMTPServer;

    @Column(name = "port", length = 5, nullable = false)
    private Integer port;

    @Column(name = "login", length = 64, nullable = false)
    private String login;

    @Column(name = "password", length = 512, nullable = false)
    private String password;

    @Column(name = "mailaddressfrom", length = 64, nullable = false)
    private String mailAddressFrom;

    @Column(name = "mailserverstarttls", length = 1, nullable = false)
    private Boolean mailServerStartTls;

    @Column(name = "mailserverauth", length = 1, nullable = false)
    private Boolean mailServerAuth;

    public Mailset(String SMTPServer, Integer port, String login, String password, String mailAddressFrom, Boolean mailServerStartTls, Boolean mailServerAuth) {
        this.SMTPServer = SMTPServer;
        this.port = port;
        this.login = login;
        this.password = password;
        this.mailAddressFrom = mailAddressFrom;
        this.mailServerStartTls = mailServerStartTls;
        this.mailServerAuth = mailServerAuth;
    }

    public Mailset() {
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
