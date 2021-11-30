package com.luv2code.springboot.thymeleafdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class LoginController {

        @GetMapping("/login-page")
        public String showMyLoginPage() {

            return "new-login";

        }

        // add request mapping for /access-denied

        @GetMapping("/access-denied")
        public String showAccessDenied() {
            return "access-denied";
        }

    }

