package com.example.week7.controller;

import com.example.week7.dto.UserDTO;
import com.example.week7.model.User;
import com.example.week7.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserService service;
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

    @PostMapping("/sign-in") //localhost:8080
    public String validateUser(@ModelAttribute User user, Model model, HttpServletRequest httpServletRequest){
        String validate = service.createUser(new UserDTO(user.getUsername(),user.getEmail(),user.getPassword()));
        if (validate.equals("successful created account")){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user_id",user.getId());
            session.setAttribute("user_email",user.getEmail());
            model.addAttribute("session_id",session.getAttribute("user_id"));
            model.addAttribute("session_email",session.getAttribute("user_email"));
            model.addAttribute("status","succesful");
            return "index";

        }

        return "login";
    }
}
