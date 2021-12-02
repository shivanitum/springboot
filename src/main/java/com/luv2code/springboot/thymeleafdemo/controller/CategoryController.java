package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.converter.BookConverter;
import com.luv2code.springboot.thymeleafdemo.converter.CategoryConverter;
import com.luv2code.springboot.thymeleafdemo.converter.UserConverter;
import com.luv2code.springboot.thymeleafdemo.dto.BookDTO;
import com.luv2code.springboot.thymeleafdemo.dto.CategoryDTO;
import com.luv2code.springboot.thymeleafdemo.dto.UserDTO;
import com.luv2code.springboot.thymeleafdemo.entity.Authority;
import com.luv2code.springboot.thymeleafdemo.entity.Book;
import com.luv2code.springboot.thymeleafdemo.entity.Category;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import com.luv2code.springboot.thymeleafdemo.service.BookService;
import com.luv2code.springboot.thymeleafdemo.service.CategoryService;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/categories")
@Controller
public class CategoryController {
    // load employee data

    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    BookConverter bookConverter;

    @Autowired
    CategoryConverter categoryConverter;

    @Autowired
    UserConverter userConverter;

    @Autowired
    public CategoryController(CategoryService theCategoryService) {
        categoryService=theCategoryService;}



    // add mapping for "/list"

    @GetMapping("/list")
        public String listCategories(Model theModel) {

           List<CategoryDTO>theCategories=categoryConverter.entityToDto(categoryService.findAll());
           // add to the spring model
           theModel.addAttribute("categories", theCategories);
        String name = SecurityContextHolder.getContext().getAuthentication().getName(); //**
        UserDTO userDTO = userConverter.entityToDto(userService.findByUsername(name));
        theModel.addAttribute("user",userDTO);
           return "category/list-categories";
        }
    @GetMapping("/list-books")
    public String listCategories(@RequestParam("categoryId") int theId,Model model) {

        CategoryDTO categoryDTO= categoryConverter.entityToDto(categoryService.findById(theId));
        List<BookDTO>books= bookConverter.entityToDto(categoryDTO.getBooks());
        model.addAttribute("books",books);
        return "category/list-category-books";
    }

    @GetMapping("/add-book")
    public String addBooks(@RequestParam("categoryId") int theId,Model model) {

        CategoryDTO categoryDTO=categoryConverter.entityToDto(categoryService.findById(theId));
        model.addAttribute("category",categoryDTO);
        BookDTO book=new BookDTO();
        model.addAttribute("book",book);
        return "category/book-form";
    }

    @GetMapping("/add-category")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        CategoryDTO categoryDTO= categoryConverter.entityToDto(new Category());
        theModel.addAttribute("category", categoryDTO);
        return "category/category-form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") CategoryDTO theCategory) {

        // save the category
        Category categoryEntity=categoryConverter.dtoToEntity(theCategory);
        categoryService.save(categoryEntity);

        // use a redirect to prevent duplicate submissions
        return "redirect:/categories/list";
    }

    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user",user);

        return "sign-up";}

    @PostMapping("/save-book")
    public String saveBook(@Valid @ModelAttribute("book") BookDTO theBook,@RequestParam("categoryId") int id,BindingResult bindingResult) {

        // save the book

        Category category=categoryService.findById(id);
        Book bookEntity=bookConverter.dtoToEntity(theBook);
        category.add(bookEntity);
        bookEntity.setCategory(category);

        if(bookEntity.getTitle().equals("")){
            bindingResult.addError(new FieldError("bookEntity","title","username already exists"));
            System.out.println("Error present"+bookEntity.getTitle());
        }
        if(bindingResult.hasErrors()){
            return "category/book-form";
        }
        bookService.save(bookEntity);
        // use a redirect to prevent duplicate submissions
        return "redirect:/categories/list-books?categoryId="+id;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId") int theId) {

        // delete the category
        try{
        categoryService.deleteById(theId);}
        catch (RuntimeException e){
            return "Can't delete category when there are books!";
        }
        // redirect to /categories/list
        return "redirect:/categories/list";
    }

    @PostMapping("/register")
    public String processRegister(@Valid UserDTO userDTO, BindingResult bindingResult) {
        User user=userDTO.dtoToEntity(userDTO);
        user.setTheAuthority(new Authority(2));
        user.setEnabled(1);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if (userService.usernameAlreadyExists(user.getUsername())){
            bindingResult.addError(new FieldError("user","username","username already exists"));
        }
        if (bindingResult.hasErrors()){
            return "sign-up";
        }
        userService.save(user);

        return "register-success";
    }

}
