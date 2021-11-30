package com.luv2code.springboot.thymeleafdemo.converter;

import com.luv2code.springboot.thymeleafdemo.dto.BookDTO;
import com.luv2code.springboot.thymeleafdemo.entity.Book;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverter {
    public BookDTO entityToDto(Book book){
        BookDTO bookDTO=new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setEdition(book.getEdition());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setCategory(book.getCategory());
        return bookDTO;
    }
    public Book dtoToEntity(BookDTO bookDTO){
        Book book=new Book();
        book.setId(bookDTO.getId());
        book.setEdition(bookDTO.getEdition());
        book.setTitle(bookDTO.getTitle());
        book.setCategory(bookDTO.getCategory());
        return book;
    }
    public List<BookDTO> entityToDto(List<Book> books)
    {
        return  books.stream().map(this::entityToDto).collect(Collectors.toList());

    }


}
