package com.luv2code.springboot.thymeleafdemo.dto;

import com.luv2code.springboot.thymeleafdemo.entity.Category;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class BookDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String edition;

    private Category category;
}
