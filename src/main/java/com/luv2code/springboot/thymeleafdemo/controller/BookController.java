package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.converter.BookConverter;
import com.luv2code.springboot.thymeleafdemo.converter.UserConverter;
import com.luv2code.springboot.thymeleafdemo.dto.BookDTO;

import com.luv2code.springboot.thymeleafdemo.entity.Book;
import com.luv2code.springboot.thymeleafdemo.service.BookService;
import com.luv2code.springboot.thymeleafdemo.service.CategoryService;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@Controller
public class BookController {
    // load employee data
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookConverter bookConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    UserService userService;



    public BookController(BookService theBookService) {
        bookService = theBookService;}

    // add mapping for "/list"

        @GetMapping("/list")
        public String listBooks(Model theModel) {

            // add to the spring model
            List<BookDTO> booksDTO=bookConverter.entityToDto(bookService.findAll());
            theModel.addAttribute("books", booksDTO);

            return "books/list-books";
        }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        BookDTO theBook=bookConverter.entityToDto(new Book());

        theModel.addAttribute("book", theBook);

        return "books/book-form";
    }
   @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the book from the service
        Book theBook = bookService.findById(theId);
        //convert book entity to DTO
       BookDTO bookDTO=bookConverter.entityToDto(theBook);
        // set book as a model attribute to pre-populate the form
        theModel.addAttribute("book", bookDTO);

        // send over to our form
        return "books/book-form";
    }

   @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") BookDTO theBook) {


       //convert bookDTO to bookEntity

        bookService.save(bookConverter.dtoToEntity(theBook));


        // use a redirect to prevent duplicate submissions
        return "redirect:/books/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {

        // delete the employee
        bookService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/books/list";

    }
}
