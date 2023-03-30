package com.example.week7.controller;

import com.example.week7.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @GetMapping("/") //localhost:8080
    public String app(){
        return "index";
    }
    @GetMapping("/signup") //localhost:8080/signup
    public ModelAndView create(ModelAndView modelAndView){
        modelAndView.setViewName("signup");
        modelAndView.addObject("user",new UserDTO());

        return modelAndView;
    }
    @GetMapping("/login") //localhost:8080
    public String login(){
        return "login";
    }

    @PostMapping("/sign-out") //localhost:8080
    public String validateUser(){
        return "login";
    }
}
