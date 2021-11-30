package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
     User findByUsername(String s);
}
