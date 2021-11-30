package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dto.BookDTO;
import com.luv2code.springboot.thymeleafdemo.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
     List<Book> findAll();

    Book findById(int theId);

     void save(Book theBook);

     void deleteById(int theId);



}
