package com.luv2code.springboot.thymeleafdemo.dto;

import com.luv2code.springboot.thymeleafdemo.entity.Book;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class CategoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ToString.Exclude
    private List<Book> books;
}
