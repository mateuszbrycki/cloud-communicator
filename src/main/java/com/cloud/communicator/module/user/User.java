package com.cloud.communicator.module.user;

import com.cloud.communicator.module.userrole.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Mateusz Brycki on 12/05/2015.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_user_role")
    private UserRole role;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="mail")
    private String mail;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="is_public")
    private boolean isPublic;

    @NotNull
    @Column(name="is_enabled")
    private boolean isEnabled;

    public static Boolean DEFAULT_IS_PUBLIC = true;
    public static Boolean DEFAULT_IS_ENABLED = true;
    public static String DEFAULT_ROLE = "ROLE_USER";

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
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


}