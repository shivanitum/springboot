package com.luv2code.springboot.thymeleafdemo.service;


import com.luv2code.springboot.thymeleafdemo.dto.CategoryDTO;
import com.luv2code.springboot.thymeleafdemo.entity.Category;

import java.util.List;

public interface CategoryService {
     List<Category> findAll();

     Category findById(int theId);

     void save(Category theCategory);

     void deleteById(int theId);

     Category findByName(String name);
}
