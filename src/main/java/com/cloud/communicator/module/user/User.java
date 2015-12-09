package com.cloud.communicator.module.user;

import com.cloud.communicator.module.userrole.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_role_id")
    @JsonIgnore
    private UserRole role;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="mail")
    @JsonIgnore
    private String mail;

    @NotNull
    @Column(name="password")
    @JsonIgnore
    private String password;

    @NotNull
    @Column(name="is_active")
    @JsonIgnore
    private boolean isActive;

    public static Boolean DEFAULT_IS_ACTIVE = true;
    public static String DEFAULT_ROLE = "ROLE_USER";

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {  this.username = username; }

    @Override
    public String toString() {
        return "User: {Id: " + this.getId() +
                ", mail: " + this.getMail() +
                ", role: " + this.getRole() +
                ", username: " + this.getUsername() + "}";

    }


}