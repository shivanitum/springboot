package com.luv2code.springboot.thymeleafdemo.service;


import com.luv2code.springboot.thymeleafdemo.entity.User;

import java.util.List;


public interface UserService {
        void save(User theUser);

         User findByUsername(String s);

        boolean usernameAlreadyExists(String email);
         List<User> findAll();
    }

