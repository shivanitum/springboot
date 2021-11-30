package com.luv2code.springboot.thymeleafdemo.exceptions;

import org.springframework.ui.Model;

public class MyRunTimeException extends RuntimeException{


   public MyRunTimeException(String message){
        super(message);
    }




}
