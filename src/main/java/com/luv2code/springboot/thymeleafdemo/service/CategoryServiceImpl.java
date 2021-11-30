package com.luv2code.springboot.thymeleafdemo.service;


import com.luv2code.springboot.thymeleafdemo.converter.CategoryConverter;
import com.luv2code.springboot.thymeleafdemo.dao.CategoryRepository;
import com.luv2code.springboot.thymeleafdemo.dto.CategoryDTO;
import com.luv2code.springboot.thymeleafdemo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository theCategoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        theCategoryRepository=categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return theCategoryRepository.findAll();
    }

    @Override
    public Category findById(int theId) {
        Optional<Category> result = theCategoryRepository.findById(theId);

        Category theCategory = null;


        if (result.isPresent()) {
            theCategory = result.get();

        }
        else {
            // we didn't find the category
            throw new RuntimeException("Did not find category id - " + theId);
        }

        return theCategory;
    }



    @Override
    public void save(Category theCategory) {
        theCategoryRepository.save(theCategory);
    }

    @Override
    public void deleteById(int theId) {
        theCategoryRepository.deleteById(theId);
    }

    @Override
    public Category findByName(String name){
        List<Category> categories=theCategoryRepository.findAll();
        for(Category name1:categories){
            if(name1.getName().equals(name)){
                return name1;
            }
        }
        return null;
    }
}
