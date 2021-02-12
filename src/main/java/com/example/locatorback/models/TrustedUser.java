package com.example.locatorback.models;


import javax.persistence.*;

@Entity
public class TrustedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User user;
    private String trustedUser;

    public TrustedUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTrustedUser() {
        return trustedUser;
    }

    public void setTrustedUser(String trustedUser) {
        this.trustedUser = trustedUser;
    }
}
