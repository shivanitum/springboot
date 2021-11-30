package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.converter.BookConverter;
import com.luv2code.springboot.thymeleafdemo.dao.BookRepository;

import com.luv2code.springboot.thymeleafdemo.dto.BookDTO;
import com.luv2code.springboot.thymeleafdemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        bookRepository = theBookRepository;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books=bookRepository.findAll();
        return books;
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theBook = null;

        if (result.isPresent()) {
            theBook = result.get();
        }
        else {
            // we didn't find the book
            throw new RuntimeException("Did not find book id - " + theId);
        }

        return theBook;
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }




}
// add error page