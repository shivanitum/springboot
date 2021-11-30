package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // that's it ... no need to write any code LOL!


}
