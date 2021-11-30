package com.luv2code.springboot.thymeleafdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username")
    @NotNull(message = "Username Required")
    @Pattern(regexp = "[^0-9]*",message = "Numbers not allowed")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password is required")
    @Size(min = 1, message = "is required")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name="authorities_id")
    private Authority theAuthority;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int userId, String username, String password, int enabled,  Authority theAuthority) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.theAuthority = theAuthority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getTheAuthority() {
        return theAuthority;
    }

    public void setTheAuthority(Authority theAuthority) {
        this.theAuthority = theAuthority;
    }
}
