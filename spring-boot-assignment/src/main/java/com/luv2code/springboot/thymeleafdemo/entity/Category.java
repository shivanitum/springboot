package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank(message = "requires a valid name!")
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="category", cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Book> books;

   public Category(){}

    public Category(String name){
       this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // add convenience methods for bi-directional relationship

    public void add(Book tempBook) {

        if (books == null) {
            books = new ArrayList<>();
        }

        books.add(tempBook);

        tempBook.setCategory(this);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
