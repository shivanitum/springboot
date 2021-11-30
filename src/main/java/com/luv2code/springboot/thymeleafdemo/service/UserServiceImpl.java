package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.UserRepository;
import com.luv2code.springboot.thymeleafdemo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }
    @Override
    public User findByUsername(String s)
    {
        return userRepository.findByUsername(s);
    }


    public boolean usernameAlreadyExists(String email){

        return (findByUsername(email) != null);
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
}