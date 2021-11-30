package com.luv2code.springboot.thymeleafdemo.converter;


import com.luv2code.springboot.thymeleafdemo.dto.CategoryDTO;

import com.luv2code.springboot.thymeleafdemo.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    public CategoryDTO entityToDto(Category category){
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setBooks(category.getBooks());
        return categoryDTO;
    }

    public Category dtoToEntity(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setBooks(categoryDTO.getBooks());
        return category;
    }

    public List<CategoryDTO> entityToDto(List<Category> categories)
    {
        return categories.stream().map(this::entityToDto).collect(Collectors.toList());
    }


}
