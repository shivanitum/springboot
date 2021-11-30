package com.luv2code.springboot.thymeleafdemo.dto;

import com.luv2code.springboot.thymeleafdemo.entity.Authority;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String password;
    private int enabled;

    private Authority theAuthority;

}
