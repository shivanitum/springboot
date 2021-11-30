package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank(message = "this field cannot be empty")
    @Column(name="title")
    private String title;

    @NotBlank(message = "this field cannot be empty")
    @Column(name="edition")
    private String edition;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    public Book(){

   }
   public Book(String title,String editon){
        this.title=title;
        this.edition=editon;
   }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }



    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +", category="+getCategory()+
                '}';
    }
}
